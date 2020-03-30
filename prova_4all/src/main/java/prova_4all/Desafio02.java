package prova_4all;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Desafio02 {
	
	private WebDriver driver;
	
	@Before
	public void Inicializar() {
		// Informar o caminho do driver do Navegador e abrir o Navegador na página informada
		System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
		driver = new FirefoxDriver();	
		driver.get("https://shopcart-challenge.4all.com/");		
	}

	@Rule
	public TestName testname = new TestName();
	
	@After
	public void Finalizar() throws IOException {

		// Capturar de Tela
		TakesScreenshot ss = ((TakesScreenshot) driver);
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("target" + File.separator +
				"screenshot" + File.separator + testname.getMethodName() + ".jpg"));
		
		// Clicar no botão Finalizar Compra
		WebElement elementFinalizarCompra = driver.findElement(By.id("finish-checkout-button"));
		elementFinalizarCompra.click();	
		
		// Validar "Pedido realizado com sucesso!"
		WebElement PedidoRealizado = driver.findElement(By.xpath("//h2[@class=\"sc-dNLxif jyncPa\"]")); 
		Assert.assertEquals("Pedido realizado com sucesso!", PedidoRealizado.getText());

		// Clicar no botao Fechar
		WebElement Fechar = driver.findElement(By.xpath("//button[@class='close-modal sc-jqCOkK ippulb']"));
		Fechar.click();

		driver.quit();		
	}
	
	@Test
	public void Challenge02() {
		
		// Clicar em Selecionar Categoria - Exibir suas opcoes
		WebElement elementSelecionarCategoria = driver.findElement(By.id("open-categories-btn"));
		elementSelecionarCategoria.click();
		
		// Clicar na opcao Bebidas
		WebElement elementBebidas = driver.findElement(By.id("category-0"));
		elementBebidas.click();
		
		// Adicionar ao Carrinho Coca-Cola
		WebElement elementAdicionarCarrinhoCocaCola = driver.findElement(By.id("add-product-0-btn"));
		elementAdicionarCarrinhoCocaCola.click();
		
		// Adicionar ao Carrinho Fanta
		WebElement elementAdicionarCarrinhoFanta = driver.findElement(By.id("add-product-1-btn"));
		elementAdicionarCarrinhoFanta.click();

		// Adicionar ao Carrinho Agua
		WebElement elementAdicionarCarrinhoAgua = driver.findElement(By.id("add-product-2-btn"));
		elementAdicionarCarrinhoAgua.click();
		
		// Clicar em Selecionar Categoria - Exibir suas opcoes
		elementSelecionarCategoria.click();

		// Selecionar a opcao Todos
		WebElement elementTodos = driver.findElement(By.id("category-all"));
		elementTodos.click();

		// Adicionar ao Carrinho Risole
		WebElement elementAdicionarCarrinhoRisole = driver.findElement(By.id("add-product-3-btn"));
		elementAdicionarCarrinhoRisole.click();
		
		// Acessar ao Carrinho
		WebElement elementCarrinho = driver.findElement(By.id("cart-btn"));
		elementCarrinho.click();		
		
		//Aumentar a Quantidade do Risole em 9
		WebElement elementRisoleAumentarQtde = driver.findElement(By.id("add-product-3-qtd"));
		for (int i = 0; i < 9;i++ ) {
			elementRisoleAumentarQtde.click();
		}
		
		//Diminuir a Quantidade do Risole em 5
		WebElement elementRisoleDiminuirQtde = driver.findElement(By.id("remove-product-3-qtd"));
		for (int i = 0; i < 5;i++ ) {
			elementRisoleDiminuirQtde.click();
		}
		
		// Validar o valor SubTotal da compra
		Assert.assertEquals("R$ 36,00",
				driver.findElement(By.id("subtotal-price")).getText());
	}
}
