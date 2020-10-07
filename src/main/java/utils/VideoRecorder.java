package utils;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.Point;
import org.openqa.selenium.Dimension;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorder {

    private static final String RECORD_DIRECTORY = "\\video\\";
    private static ScreenRecorder screenRecorder;

    public static void startRecording(WebDriver driver) {
        try {
            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                    .getDefaultConfiguration();

            File dir = new File(RECORD_DIRECTORY);

            // Record only WebDriver window area to reduce the size of the video
            Point point = driver.manage().window().getPosition();
            Dimension dimension = driver.manage().window().getSize();

            Rectangle rectangle = new Rectangle(point.x, point.y, dimension.width, dimension.height);

            screenRecorder = new ScreenRecorder(gc, rectangle,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey,
                            MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
                            ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey,
                            ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey,
                            24, FrameRateKey, Rational.valueOf(15), QualityKey,
                            1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
                            "black", FrameRateKey, Rational.valueOf(30)),
                    null, dir);
            screenRecorder.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String stopRecording(String recordName) throws Exception {
        screenRecorder.stop();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        File newFileName = new File(String.format("%s%s %s.avi",
                RECORD_DIRECTORY, recordName,
                dateFormat.format(new Date())));

        // Rename video file
        if (recordName != null) {
            screenRecorder.getCreatedMovieFiles().get(0).renameTo(newFileName);
        }
        return newFileName.toString();
    }
}
