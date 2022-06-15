import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.
        *;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing extends JFrame {

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;
    public static final int DEFAULT_COMPONENT_HEIGHT = 90;


    public ImageProcessing() throws IOException {

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // text filed and button - when button clicked we call load image function
        JLabel label = new JLabel();
        JTextField field = new JTextField();
        field.setBackground(Color.ORANGE);
        field.setBounds(WINDOW_WIDTH/2, 20, 110,110);
        this.add(field);

        JButton sendProfileName = new JButton("send");
        sendProfileName.setBounds(field.getX(), field.getY()+field.getHeight() ,field.getWidth(), field.getHeight());
        this.add(sendProfileName);
        sendProfileName.addActionListener( (event) -> {
            String s = field.getText();
            label.setText("https://facebook.com/"+s);
            try {
                loadImage(label, this);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

    this.setVisible(true);


    }




    public static void loadImage(JLabel label, ImageProcessing imageProcessing) throws IOException, InterruptedException {

        // get profile pic from facebook and store it in PC folder (My folder here is C:\Images) the name of the image is profilePic
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\selenium\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();
        driver.get(label.getText());
        Thread.sleep(6000);

        // find the element of the profile photo and store it in folder PC - optional - I guss there is another ways
        WebElement element = driver.findElement(By.xpath("//div[@class='b3onmgus e5nlhep0 ph5uu5jm ecm0bbzt spb7xbtv bkmhp75w emlxlaya s45kfl79 cwj9ozl2']//div[@class='q9uorilb l9j0dhe7 pzggbiyp du4w35lb']//*[name()='svg']//*[name()='g' and contains(@mask,'url(#jsc_c')]//*[name()='image' and contains(@x,'0')]"));
        String src = element.getAttribute("xlink:href");
        URL imageUrl = new URL(src);
        BufferedImage scanImage = ImageIO.read(imageUrl);
        File file = new File("C:\\Images\\profilePic.jpg");
        ImageIO.write(scanImage, "jpg", file);


        
        // upload the photo form facebook to swing window
        Thread.sleep(4000);
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon("C:/Images/profilePic.jpg"));
        Dimension size = image.getPreferredSize();
        System.out.println(size);
        image.setBounds(0, 0, size.width, size.height);
        imageProcessing.add(image);

        imageProcessing.setVisible(true);


        // *the next step is prcess and make filters to photo
        /*
        File file = new File("C:/Images/profilePic.jpg");
        if (file.exists()){
            // important: read is receive URL path....
            BufferedImage imageProcess = ImageIO.read(file);
            for (int x = 0; x< imageProcess.getWidth(); x++){
                for (int y = 0; y < imageProcess.getHeight(); y++ ){
                    int pixel = imageProcess.getRGB(x,y);
                    Color color = new Color(pixel);
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    Color newColor = new Color(green, blue, red);
                    imageProcess.setRGB(x, y, newColor.getRGB());

                }
            }


            File output = new File("C:/Images/yuda2.jpeg");
            ImageIO.write(imageProcess, "jpeg", output);
        }

 */

    }


    public static void main(String[] args) throws IOException {

        new ImageProcessing();

    }
}
