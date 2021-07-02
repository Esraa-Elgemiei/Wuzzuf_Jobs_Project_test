package DataPreparation;

import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;
import smile.data.Tuple;
import smile.io.Read;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JobDAO
{
    private DataFrame jobDataFrame;

    public DataFrame readCSV(String path) {

        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader ();
        DataFrame df = null;
        try {
            df = Read.csv (path, format);
            System.out.println("========================DataFrame Structure========================");
            System.out.println(df.structure ());
            /*System.out.println("========================DataFrame Summary========================");
            System.out.println(df.summary ());*/

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace ();
        }
        jobDataFrame = df;
        return df;
    }
    public DataFrame cleanDataFrame(DataFrame data){

        //OmitNullRows
        DataFrame cleanData= data.omitNullRows ();
        System.out.println ("Number of non Null rows is: "+cleanData.nrows ());

        //Remove Duplicates

        List<Job> jobList = getJobList().stream().distinct().collect(Collectors.toList());
        System.out.println ("Number of unique rows is: "+jobList.size());
        /*List<Tuple> rows = cleanData.stream().distinct().collect(Collectors.toList());
        cleanData=DataFrame.of(rows);*/
        return cleanData;
    }

    public List<Job> getJobList() {
        assert jobDataFrame != null;
        List<Job> jobs = new ArrayList<>();
        for (Tuple t : jobDataFrame.stream().collect(Collectors.toList())) {
            Job job = new Job();
            job.setTitle((String) t.get("Title"));
            job.setCompany((String) t.get("Company"));
            job.setLocation((String) t.get("Location"));
            job.setType((String) t.get("Type"));
            job.setLevel((String) t.get("Level"));
            job.setYearsExp((String) t.get("YearsExp"));
            job.setCountry((String) t.get("Country"));
            job.setSkills((String) t.get("Skills"));
            jobs.add(job);
        }
        return jobs;
    }
}
