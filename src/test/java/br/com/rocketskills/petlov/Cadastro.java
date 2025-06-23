package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class PontoDoacao{
	String nome;
	String email;
	String cep;
	String rua;
	Integer numero;
	String complemento;
	String pets;

	public PontoDoacao(String nome, String email, String cep, String rua, Integer numero, String complemento, String pets) {
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.pets = pets;
	}
}

class Cadastro {

	private void submeterFormulario(PontoDoacao pontoDoacao) {
		$("input[placeholder='Nome do ponto de doação']").setValue(pontoDoacao.nome);
		$("input[name='email']").setValue(pontoDoacao.email);
		$("input[name='cep']").setValue(pontoDoacao.cep);
		$("input[value='Buscar CEP']").click();
		$("input[name='addressNumber']").setValue(pontoDoacao.numero.toString());
		$("input[name='addressDetails']").setValue(pontoDoacao.complemento);
		$(By.xpath("//span[normalize-space()='" + pontoDoacao.pets + "']")).click();
		$(".button-register").click();
	}

	private void acessaSite() {
		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));
	}
	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void createDonationPoint() {

	// Pre condicao
	PontoDoacao pontoDoacao = new PontoDoacao(
		"Nome do ponto de doação",
		"email@email.com",
		"04534011",
		"Rua",
		199,
		"Apto 101",
		"Cachorros"
	);
		acessaSite();

		// Ação
		submeterFormulario(pontoDoacao);

		// Resultado esperado
		$("#success-page h1").shouldHave(text("Você fez a diferença"));

	}

		@Test
	@DisplayName("Não pode cadastrar com email Invalido")
	void emailIncorreto() {

	// Pre condicao
	PontoDoacao pontoDoacao = new PontoDoacao(
		"nome 1",
		"emailincorreto.com",
		"04534011",
		"Rua",
		199,
		"Apto 101",
		"Cachorros"
	);
		
		acessaSite();
		
		// Ação
		submeterFormulario(pontoDoacao);

		// Resultado esperado
		$(".alert-error").shouldHave(text("Informe um email válido"));
	}
}
