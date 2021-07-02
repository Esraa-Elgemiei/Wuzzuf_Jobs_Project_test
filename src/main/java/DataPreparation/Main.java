package DataPreparation;

import smile.data.DataFrame;
import smile.data.Tuple;
import smile.data.type.StructType;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static String jobPath = "src/main/resources/data/Wuzzuf_Jobs.csv";

    public static void main(String[] args) throws IOException {
        JobDAO dao = new JobDAO();
        //create dataFrame & it's structure
        DataFrame jobData = dao.readCSV(jobPath);

        System.out.println("========================DataFrame=======================");
        //jobData.forEach(System.out::println);

        StructType schema = jobData.schema();
        //System.out.println(schema);
        //List<Job> jobList = dao.getJobList().stream().distinct().collect(Collectors.toList());
        List<Tuple> jobList = jobData.stream().distinct().collect(Collectors.toList());
        DataFrame cleandata = DataFrame.of(jobList, schema);
        System.out.println("size of cleaned dataset: "+cleandata.size());



        System.out.println("========================DataFrame lists========================");
       // jobList.forEach(System.out::println);
        /*System.out.println("size of original dataset: "+jobData.size());
        System.out.println("size of original dataset: "+jobList.size());
        jobData=dao.cleanDataFrame(jobData);
        System.out.println("size of cleaned dataset: "+jobData.size());*/

    }
}
