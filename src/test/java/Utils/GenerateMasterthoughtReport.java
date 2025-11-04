package Utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GenerateMasterthoughtReport {

    public static void generateReport() {
        File reportOutputDirectory = new File("target/masterthought-report");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-report/Cucumber.json");

        String projectName = "Selenium BDD Framework";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Platform", System.getProperty("os.name"));
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Cucumber Version", "7.20.1");
        configuration.addClassifications("Selenium Version", "4.24.0");
        configuration.addClassifications("Java Version", System.getProperty("java.version"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();

        System.out.println("✅ Masterthought HTML report generated at: " + reportOutputDirectory.getAbsolutePath());
    }
}
