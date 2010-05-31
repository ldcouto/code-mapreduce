package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;

/**
 * This class is the mapper for the {@link ImportsByFile} job. It extends
 * {@link ImportsCommonMapper} which handles the map step for this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByFileMapper extends ImportsCommonMapper<Text, Text, Text, Text>
{}