package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class SeleniumCadastro {

	WebDriver driver;

	@BeforeEach
	void start() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@AfterEach
	void finish() {
		driver.close();
	}

	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void createDonationPoint() {


		driver.get("https://petlov.vercel.app/signup");

		WebElement title = driver.findElement(By.cssSelector("h1"));

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(d -> title.isDisplayed());

		assertEquals("Cadastro de ponto de doação", title.getText(), "Verificando pagina de cadastro");

		WebElement  name = driver.findElement(By.cssSelector("input[placeholder='Nome do ponto de doação']"));
		name.sendKeys("Ponto de Doação");

		WebElement  email = driver.findElement(By.name("email"));
		email.sendKeys("email@email.com");

		WebElement  cep = driver.findElement(By.name("cep"));
		cep.sendKeys("04534011");

		WebElement  cepButton = driver.findElement(By.cssSelector("input[value='Buscar CEP']"));
		cepButton.click();

		WebElement  number = driver.findElement(By.name("addressNumber"));
		number.sendKeys("199");

		WebElement  complement = driver.findElement(By.name("addressDetails"));
		complement.sendKeys("Apto 101");

		driver.findElement(By.xpath("//span[normalize-space()='Cachorros']")).click();

		driver.findElement(By.className("button-register")).click();

		// validacao do sucesso

		WebElement  success = driver.findElement(By.cssSelector("#success-page h1"));
		assertEquals("Você fez a diferença!", success.getText(), "Verificando sucesso de cadastro");

	}
}
