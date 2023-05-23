package automation_project;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class PageObjectClass {
	@FindBy(xpath="//a[text()='Home']") WebElement home;//Email id textbox
	@FindBy(xpath="//a[text()='Football']") WebElement football;//Email id textbox
	@FindBy(xpath="//a[text()='Busketball']") WebElement basketball;//Email id textbox
	@FindBy(xpath="//a[text()='Kriket']") WebElement cricket;//Email id textbox
	@FindBy(xpath="//a[text()='Cibersport']") WebElement cybersport;//Email id textbox
}
