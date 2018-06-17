package com.example.guto.pecame.utils;

import com.example.guto.pecame.modelo.PedidoModelo;

import java.util.List;

public interface PedidoCallback {
    void onPedidoRemovido(int codproduto);
    List<PedidoModelo> getPedidos();
    void setListaPedido(List<PedidoModelo> pedidos);
    int incrementaQuantidade(int codigo,int quantidade);
    int decrementaQuantidade(int codigo,int quantidade);
    void totalPedido(int codigo, float valorItem,int id);
}
