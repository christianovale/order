package br.com.christianovale.order.aplicacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.christianovale.order.dominio.Pedido;
import br.com.christianovale.order.dominio.Produto;
import br.com.christianovale.order.infra.PedidoRepository;
import br.com.christianovale.order.infra.ProdutoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    // Recebe um pedido e calcula o valor total
    public Pedido calcularValorPedido(Pedido pedido) {
        double valorTotal = pedido.getProdutos().stream()
                                 .mapToDouble(Produto::getPreco)
                                 .sum();
        pedido.setValorTotal(valorTotal);
        return pedidoRepository.save(pedido);
    }

    // Verifica duplicação de pedidos
    public boolean verificarDuplicidade(Pedido pedido) {
        return pedidoRepository.existsById(pedido.getId());
    }

    // Cria e persiste um pedido
    public Pedido criarPedido(Pedido pedido) {
        if (verificarDuplicidade(pedido)) {
            throw new IllegalArgumentException("Pedido já existe.");
        }
        return calcularValorPedido(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
