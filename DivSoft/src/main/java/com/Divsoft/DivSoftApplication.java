package com.Divsoft;







import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





@SpringBootApplication
public class DivSoftApplication implements CommandLineRunner {
	
	/*@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private VendasRepository compraRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;*/

	
	public static void main(String[] args) {
		SpringApplication.run(DivSoftApplication.class, args);
	}
	
	
	public void run(String... args) throws Exception  {
		/*try {
			
		
		Produto produto = new Produto();
		produto.setQuantidade(20);
		produto.setValor(500);
		produto.setDescricao("Caixa");
		
		
		
		
		Cliente cliente = new Cliente();
		cliente.setNome("Inacio");
		cliente.setCpf(12364789);
		cliente.setEndereco("Clemento");
		cliente.setTelefone(81236478);
		
		Vendas vendas = new Vendas();
		vendas.setComprador(cliente.getNome());
		vendas.setData_compra("08/03/2020");
		vendas.setQuantidade(2);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("inacio");
		funcionario.setLogin("inacioalves");
		funcionario.setSenha(123);
		
		Estoque estoque = new Estoque();
		estoque.setEntrada(12);
		estoque.setSaida(10);
		
		produtoRepository.saveAll(Arrays.asList(produto));
		clienteRepository.saveAll(Arrays.asList(cliente));
		compraRepository.saveAll(Arrays.asList(vendas));
		funcionarioRepository.saveAll(Arrays.asList(funcionario));
		estoqueRepository.saveAll(Arrays.asList(estoque));
		
		} catch (Exception e) {
			System.out.println("Erro"+e);
		}*/
	}

}
