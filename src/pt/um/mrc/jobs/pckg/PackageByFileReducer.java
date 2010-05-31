package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.IdentityReducer;

/**
 * This class is the reducer for the {@link PackageByFile} job. It extends the
 * {@link IdentityReducer} class which handles the reduce step for this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class PackageByFileReducer extends IdentityReducer<Text, Text, Text, Text>
{}
