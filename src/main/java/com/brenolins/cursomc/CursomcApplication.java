package com.brenolins.cursomc;

import java.text.SimpleDateFormat;
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
import com.brenolins.cursomc.domain.ItemPedido;
import com.brenolins.cursomc.domain.Pagamento;
import com.brenolins.cursomc.domain.PagamentoComBoleto;
import com.brenolins.cursomc.domain.PagamentoComCartao;
import com.brenolins.cursomc.domain.Pedido;
import com.brenolins.cursomc.domain.Produto;
import com.brenolins.cursomc.domain.enums.EstadoPagamento;
import com.brenolins.cursomc.domain.enums.TipoCliente;
import com.brenolins.cursomc.repository.CategoriaRepository;
import com.brenolins.cursomc.repository.CidadeRepository;
import com.brenolins.cursomc.repository.ClienteRepository;
import com.brenolins.cursomc.repository.EnderecoRepository;
import com.brenolins.cursomc.repository.EstadoRepository;
import com.brenolins.cursomc.repository.ItemPedidoRepository;
import com.brenolins.cursomc.repository.PagamentoRepository;
import com.brenolins.cursomc.repository.PedidoRepository;
import com.brenolins.cursomc.repository.ProdutoRepository;

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

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Alagoas");

		Cidade cid1 = new Cidade(null, "Recife", est1);
		Cidade cid2 = new Cidade(null, "Maceió", est2);
		Cidade cid3 = new Cidade(null, "Jaboatão", est1);

		est1.getCidades().addAll(Arrays.asList(cid1, cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua das Flores", "300", "Apt 203", "Jardim", "38220834", cli1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid3);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pedido ped3 = new Pedido(null, sdf.parse("30/10/2001 19:00"), cli1, e2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2,ped3));

		Pagamento pag1 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:01"),
				sdf.parse("12/10/2011 00:02"));
		ped1.setPagamento(pag1);

		
		Pagamento pag2 = new PagamentoComCartao(null, EstadoPagamento.CANCELADO, ped1, 6);
		ped2.setPagamento(pag2);
		

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 2000.00, 1);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 80.00, 2);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 800.0, 1);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

	}

}
