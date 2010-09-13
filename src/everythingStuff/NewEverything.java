package everythingStuff;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;

import pt.um.mrc.jobs.imprt.ImportsByFile;
import pt.um.mrc.jobs.imprt.ImportsByPackage;
import pt.um.mrc.jobs.imprt.PkgAndClassCache;
import pt.um.mrc.jobs.mccabe.McCabeByClass;
import pt.um.mrc.jobs.mccabe.McCabeByClassMisc;
import pt.um.mrc.jobs.mccabe.McCabeByFile;
import pt.um.mrc.jobs.mccabe.McCabeByMethod;
import pt.um.mrc.jobs.mccabe.McCabeByPackage;
import pt.um.mrc.jobs.pckg.PackageByFile;
import pt.um.mrc.jobs.volume.VolumeByClass;
import pt.um.mrc.jobs.volume.VolumeByClassMisc;
import pt.um.mrc.jobs.volume.VolumeByFile;
import pt.um.mrc.jobs.volume.VolumeByFileMisc;
import pt.um.mrc.jobs.volume.VolumeByMethod;
import pt.um.mrc.jobs.volume.VolumeByPackage;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

public class NewEverything {

	private static Log LOG = LogFactory.getLog(NewEverything.class);

	
	public static void main(String[] args) {
		
		String[] args1;
		String[] args2;
		args1 = new String[5];
		args2 = new String[5];
		
		args1 = new String[5];
		
		int i=0;
		for (i=0; i<5; i++)
			args1[i] = args[i];
		
		args2 = new String[5];
		args2[0] = args[0];
		for (int j=1; j<5; j++){
			args2[j] = args[i];
			i++;
		}
		
		String[] args3=null;
		args3 = new String[2];
		args3[0] = args[0];
		args3[1] = args[9];
		
		PackageByFile pbf = new PackageByFile();
		LOG.info("Running PackageByFile");
		int status = JobRunner.startJob(args3, pbf);
		
		if (status != 0)	
			System.exit(status);
		
		VolumeByMethod vbm = new VolumeByMethod();
		VolumeByClass vbc = new VolumeByClass();
		VolumeByFile vbf = new VolumeByFile();
		VolumeByPackage vbp = new VolumeByPackage();

		status = volumeChainer(vbm, vbc, vbf, vbp, args1);

		if (status != 0)	
			System.exit(status);
		
		McCabeByMethod mbm = new McCabeByMethod();
		McCabeByClass mbc = new McCabeByClass();
		McCabeByFile mbf = new McCabeByFile();
		McCabeByPackage mbp = new McCabeByPackage();

		status = cycloChainer(mbm, mbc, mbf, mbp, args2);

		if (status != 0)	
			System.exit(status);
		
		status =impRunner(args);
		
		System.exit(status);
	}
	
	private static int impRunner(String []args)
	{
		int status=0;
		String[] ibfargs=null;
		ibfargs = new String[2];
		ibfargs[0] = args[0];
		ibfargs[1]= args[10];
		
		
		String[] ibpargs=null;
		ibpargs = new String[2];
		ibpargs[0] = args[0];
		ibpargs[1] = args[11];
		
	    PkgAndClassCache job1 = new PkgAndClassCache();
	    ImportsByFile job2 = new ImportsByFile();
	    Path cache = new Path("tmpCacheIBF/");
		LOG.info("Running ImportsByFile");
	    status = JobRunner.startCachedJob(ibfargs, job1, job2, cache);
	 
	    if (status != 0)	
			System.exit(status);
		
        PkgAndClassCache job3 = new PkgAndClassCache();
        ImportsByPackage job4 = new ImportsByPackage();
        Path cache2 = new Path("tmpCacheIBP/");
		LOG.info("Running ImportsByPackage");
        status = JobRunner.startCachedJob(ibpargs, job3, job4, cache2);
	    
		return status;
	}
	
	private static int cycloChainer(JobInformable jbm, JobInformable jbc, JobInformable jbf, JobInformable jbp, 
			String[] args){

		String[] argsMBM=null;
		argsMBM = new String[2];
		argsMBM[0] = args[0];
		argsMBM[1] = args[1];

		String[] argsMBC=null; 
		argsMBC = new String[3];
		argsMBC[0] = args[1];
		argsMBC[1] = args[0];
		argsMBC[2] = args[2];

		String [] argsMBF =null;
		argsMBF = new String[2];
		argsMBF[0] = args[2];
		argsMBF[1] = args[3];
		
		String [] argsMBP=null;
		argsMBP = new String[2];
		argsMBP[0] = args[3];
		argsMBP[1] = args[4];
		
		int status = 0;
		LOG.info("Running McCabeByMethod");
		status = JobRunner.startJob(argsMBM, jbm);
		if (status == 0) {
	        McCabeByClassMisc jc = new McCabeByClassMisc();
	        String tempClassFolder = "tmpClsLoc/";
			LOG.info("Running McCabeByClass");
	        status = JobRunner.startJob(argsMBC,jc, jbc,tempClassFolder); 
		if (status == 0) {
			LOG.info("Running McCabeFile");
			status = JobRunner.startJob(argsMBF,jbf);
				if (status == 0){
					LOG.info("Running McCabeByPackage");
					status = JobRunner.startJob(argsMBP, jbp);
				}
			}
		}
		return status;
	}
	
	private static int volumeChainer(JobInformable jbm, JobInformable jbc, JobInformable jbf, JobInformable jbp, 
			String[] args){
		String[] argsVBM=null;
		argsVBM = new String[2];
		argsVBM[0] = args[0];
		argsVBM[1] = args[1];

		
		String[] argsVBC=null; 
		argsVBC = new String[3];
		argsVBC[0] = args[1];
		argsVBC[1] = args[0];
		argsVBC[2] = args[2];

		String [] argsVBF =null;
		argsVBF = new String[3];
		argsVBF[0] = args[2];
		argsVBF[1] = args[0];
		argsVBF[2] = args[3];

		String [] argsVBP=null;
		argsVBP = new String[2];
		argsVBP[0] = args[3];
		argsVBP[1] = args[4];
		int status = 0;
		LOG.info("Running VolumeByMethod");
		status = JobRunner.startJob(argsVBM, jbm);
		if (status == 0) {
	        VolumeByClassMisc jc = new VolumeByClassMisc();
	        String tempClassFolder = "tmpClsLoc/";
			LOG.info("Running VolumeByClass");
			status = JobRunner.startJob(argsVBC,jc, jbc,tempClassFolder); 
		if (status == 0) {
		        VolumeByFileMisc jf = new VolumeByFileMisc();
		        String tempFileFolder = "tmpFile/";
				LOG.info("Running VolumeByFile");
		        status = JobRunner.startJob(argsVBF,jf, jbf, tempFileFolder);
				if (status == 0){
					LOG.info("Running VolumeByPackage");
					status = JobRunner.startJob(argsVBP, jbp);
				}
			}
		}
		return status;
	}

}
