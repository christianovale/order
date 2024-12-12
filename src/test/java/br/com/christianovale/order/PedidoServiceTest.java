package br.com.christianovale.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.christianovale.order.aplicacao.service.PedidoService;
import br.com.christianovale.order.dominio.Pedido;
import br.com.christianovale.order.dominio.Produto;
import br.com.christianovale.order.infra.PedidoRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    public void testCriarPedido() {
        Pedido pedido = new Pedido();
        Produto produto = new Produto();
        produto.setNome("Produto 1");
        produto.setPreco(100.0);
        pedido.setProdutos(Arrays.asList(produto));

        Pedido pedidoCriado = pedidoService.criarPedido(pedido);
        assertNotNull(pedidoCriado.getId());
        assertEquals(100.0, pedidoCriado.getValorTotal(), 0.01);
        System.out.println("teste OK - 1");
    }

    @Test
    public void testVerificarDuplicidade() {
        Pedido pedido = new Pedido();
        pedidoService.criarPedido(pedido);
        assertTrue(pedidoService.verificarDuplicidade(pedido));
        System.out.println("teste OK - 2");
    }
}
