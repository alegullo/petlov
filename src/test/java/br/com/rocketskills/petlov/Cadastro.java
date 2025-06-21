package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


class Cadastro {

/* 	WebDriver driver;

	@BeforeEach
	void start() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@AfterEach
	void finish() {
		driver.close();
	} */

	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void createDonationPoint() {


		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));

		$("input[placeholder='Nome do ponto de doação']").setValue("Nome do ponto de doação");
		$("input[name='email']").setValue("email@email.com");
		$("input[name='cep']").setValue("04534011");
		$("input[value='Buscar CEP']").click();
		$("input[name='addressNumber']").setValue("199");
		$("input[name='addressDetails']").setValue("Apto 101");
		$(By.xpath("//span[normalize-space()='Cachorros']")).click();
		$(".button-register").click();

		// validacao do sucesso
		$("#success-page h1").shouldHave(text("Você fez a diferença"));

	}
}
