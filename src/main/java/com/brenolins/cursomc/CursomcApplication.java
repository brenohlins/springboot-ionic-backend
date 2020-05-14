package com.brenolins.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brenolins.cursomc.domain.Categoria;
import com.brenolins.cursomc.domain.Cidade;
import com.brenolins.cursomc.domain.Cliente;
import com.brenolins.cursomc.domain.Endereco;
import com.brenolins.cursomc.domain.Estado;
import com.brenolins.cursomc.domain.Produto;
import com.brenolins.cursomc.domain.enums.TipoCliente;
import com.brenolins.cursomc.repository.CategoriaRepository;
import com.brenolins.cursomc.repository.CidadeRepository;
import com.brenolins.cursomc.repository.ClienteRepository;
import com.brenolins.cursomc.repository.EnderecoRepository;
import com.brenolins.cursomc.repository.EstadoRepository;
import com.brenolins.cursomc.repository.ProdutoRepository;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Info");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Produto p1 = new Produto(null, "Computador", 2220.00);
		Produto p2 = new Produto(null, "Impressora", 820.00);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Alagoas");
		
		Cidade cid1 = new Cidade(null, "Recife", est1);
		Cidade cid2 = new Cidade(null, "Maceió", est2);
		Cidade cid3 = new Cidade(null, "Jaboatão", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1,cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2, cid3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		 cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		 
		 Endereco e1 = new Endereco(null,"Rua das Flores", "300", "Apt 203", "Jardim", "38220834", cli1, cid1);
		 Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid3);
		 
		 cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		  
		 clienteRepository.saveAll(Arrays.asList(cli1));
		 enderecoRepository.saveAll(Arrays.asList(e1,e2));
		 
	}

}
