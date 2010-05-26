package pt.um.mrc.util.io;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import pt.um.mrc.util.Constants;
import pt.um.mrc.util.datatypes.FileID;

public class JFileRecordReader extends RecordReader<FileID, Text> {

	private CompilationUnit cu;
	private FileSplit fSplit;
	private FSDataInputStream fileIn;
	private int status = -1;
	private FileID currKey;
	private String everything;

	@Override
	public void close() throws IOException {
		if (fileIn != null) {
			fileIn.close();
		}
	}

	@Override
	public FileID getCurrentKey() throws IOException, InterruptedException {
		return currKey;
	}

	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		return new Text(everything);
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		if (status == 1)
			return Constants.HUNDRED_PERCENT;
		return 0;
	}

	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException,
			InterruptedException {

		Configuration job = context.getConfiguration();
		fSplit = (FileSplit) split;

		final Path file = fSplit.getPath();

		// Open the file
		FileSystem fs = file.getFileSystem(job);
		fileIn = fs.open(fSplit.getPath());

		try {
			cu = JavaParser.parse(fileIn);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		if (cu.getBeginLine() > 0) {
			String auxPKG;
			String auxFN = fSplit.getPath().getName();
			if (cu.getPackage() != null)
				auxPKG = cu.getPackage().getName().toString();
			else
				auxPKG = "<default>";
			currKey= new FileID(auxPKG, auxFN);
		}
		
		new MiscClassVisitor().visit(cu, null);

	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		if (status == -1){
			status = 1;
			return true;
		}
		return false;
	}


	private static class MiscClassVisitor extends VoidVisitorAdapter<Object> {

		public void visit(CompilationUnit c, Object arg) {
			StringBuilder sb = new StringBuilder();

			if (c.getPackage() != null) {
				sb.append(c.getPackage().toString());
				sb.append("\n");
			}

			if (c.getImports() != null) {
				for (ImportDeclaration id : c.getImports()) {
					sb.append(id.toString());
					sb.append("\n");
				}
			}

			if (c.getTypes() != null) {
				for (TypeDeclaration t : c.getTypes()) {
					if (t instanceof EnumDeclaration) {
						sb.append(t.toString());
						sb.append("\n");
					}
					if (t instanceof ClassOrInterfaceDeclaration) {
						if (((ClassOrInterfaceDeclaration) t).isInterface()) {
							sb.append(t.toString());
						}
					}
				}
			}
		}
	}

}
