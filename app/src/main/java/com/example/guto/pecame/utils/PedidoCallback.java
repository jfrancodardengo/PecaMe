package com.example.guto.pecame.utils;

import com.example.guto.pecame.modelo.PedidoModelo;

import java.util.List;

public interface PedidoCallback {
    void onPedidoRemovido(int codproduto);
    List<PedidoModelo> getPedidos();
    void setListaPedido(List<PedidoModelo> pedidos);
    void incrementaQuantidade(int codigo);
}
