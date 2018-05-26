import java.io.FileWriter;
import java.util.Arrays;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class Pull_All_Classes_Crawler 
{	 
	public static void main(String[] args) throws InterruptedException, IOException
	{
		
		System.setProperty("webdriver.chrome.driver","/Users/TahaMasood/Desktop/Selenium/chromedriver");
		
		String course_xpath = "";
		String URL = "https://uwflow.com/courses?sort_mode=course+code";
		
		FileWriter writer = new FileWriter("file_path");
		WebDriver driver = new ChromeDriver();
		driver.get(URL);
		
		String current_row = "";
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
		
		String course_id = "course-view-undefined";
		String further_information = "//*[@id=\"course-view-null\"]/div[2]/div[2]/div[3]/table/tbody/tr[";
		int traverse = 0;
		
		for(int i = 0; i < (driver.findElements(By.id(course_id))).size(); i++) 
		{
			(driver.findElements(By.id(course_id))).get(i).click();
			
			while((driver.findElements(By.xpath(further_information + traverse + "]"))).size() != 0) 
			{
				current_row = (driver.findElement(By.xpath(further_information + traverse + "]"))).getText();
				System.err.println(current_row);
			}
			
			(driver.findElements(By.id(course_id))).get(i).click();
		}
		
		driver.findElement(By.xpath(course_xpath)).click();
		driver.findElement(By.xpath(further_information)).click();
		Thread.sleep(5000);
		
		
		String xpath = "//*[@id=\"course-view-null\"]/div[2]/div[1]/div[1]/span";
		String xpath_two = "//*[@id=\"course-view-undefined\"]/div[2]/div[2]/div[3]/table/tbody";
		
		
		for(int i = 0; i < (driver.findElements(By.xpath(xpath))).size(); i++) 
		{
			(driver.findElements(By.xpath(xpath))).get(i).click();
			(driver.findElement(By.xpath("//*[@id=\"course-view-undefined\"]/div[2]/div[2]/div[2]/div/a[1]"))).click();
			
			Thread.sleep(5000);
			
			for(int j = 0; j < (driver.findElements(By.xpath(xpath))).size(); j++) 
			{
				writer.write((driver.findElement(By.xpath(xpath_two))).getText());
				System.err.println(((driver.findElement(By.xpath(xpath_two))).getText()));
			}
		}
		
		
		System.setProperty("webdriver.chrome.driver","/Users/TahaMasood/Desktop/Selenium/chromedriver");
		
		URL = "http://www.adm.uwaterloo.ca/infocour/CIR/SA/under.html";
		
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
		driver.get(URL);
	
		String partial_path = "/html/body/form/p[1]/select/option[";
		String partial_path_two = "/html/body/p[3]/table/tbody/tr[";
		String partial_path_three = "]/td[2]/table/tbody/tr[";
		String partial_path_four = "]/td[12]";
		
		int traverse_section = 1;
		int traverse_2 = 0;
		int traverse_3 = 0;
		Boolean flag = false;
	
		while((driver.findElements(By.xpath(partial_path + traverse + "]"))).size() != 0) 
		{
			(driver.findElement(By.xpath(partial_path + traverse + "]"))).click();
			(driver.findElement(By.xpath("/html/body/form/p[3]/input[1]"))).click();
			
			List <WebElement> all_classes_table = driver.findElements(By.xpath("/html/body/p[3]/table/tbody/tr/td"));
			
			for(int i = 0; i < all_classes_table.size(); i++)
			{	
				writer.write((all_classes_table.get(i)).getText());
			}
			
			if((driver.findElements(By.xpath("/html/body/p[3]/b"))).size() != 0)
			{
				if((driver.findElement(By.xpath("/html/body/p[3]/b"))).getText().equals("Sorry, but your query had no matches.")) 
				{
					flag = true;
				}
			}
		
			Thread.sleep(5000);
			
			if(!flag) 
			{
				for(int i = 0; i < 100; i++) 
				{	
					for(int j = 0; j < 100; j++) 
					{
					
						if (((driver.findElements(By.xpath(partial_path_two + i + partial_path_three + j + partial_path_four)).size()) != 0) && (!((driver.findElement(By.xpath(partial_path_two + i + partial_path_three + j + partial_path_four))).getText()).equals("")))
						{
							writer.write((driver.findElement(By.xpath(partial_path_two + i + partial_path_three + j + partial_path_four)).getText()));
							System.err.println((driver.findElement(By.xpath(partial_path_two + i + partial_path_three + j + partial_path_four)).getText()));
						}
					}
				}
			}
			
			flag = false;
			
			traverse ++;
			driver.get(URL);
		}
		
		writer.flush();
		writer.close();
		driver.quit();
		System.out.println("Done Task");
		
	}
}
