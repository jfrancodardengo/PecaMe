package com.example.guto.pecame.utils;

import com.example.guto.pecame.modelo.ProdutoModelo;

/**
 * Created by GUTO on 12/06/2018.
 */

public interface AdapterCallback {
    void onCheckItemCallback(ProdutoModelo produto,boolean isSelected);
}
