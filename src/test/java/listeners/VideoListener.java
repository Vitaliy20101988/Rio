package listeners;

import utils.VideoRecorder;
import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class VideoListener extends BaseTest implements ITestListener {

    @Attachment(value = "Video", type = "video/avi", fileExtension = ".avi")
    public byte[] saveVideo (String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            String filePath = VideoRecorder.stopRecording(iTestResult.getMethod().getMethodName());
            System.out.println("Test case: " + iTestResult.getMethod().getMethodName() + " failed and video saved to " + filePath);
            saveVideo(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        try {
            String filePath = VideoRecorder.stopRecording(iTestResult.getMethod().getMethodName());
            Files.deleteIfExists(Paths.get(filePath));
        }
        catch(NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        }
        catch(DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e) {
            System.out.println("Invalid permissions.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Deletion successful.");
    }
}


