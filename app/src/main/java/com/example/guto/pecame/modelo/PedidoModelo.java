package com.example.guto.pecame.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PedidoModelo implements Parcelable{
    private int mCodPedido;
    private int mQuantidade;
    private float mValor;
    private ProdutoModelo mProduto;

    public PedidoModelo() {
    }

    public PedidoModelo(int mCodPedido, int mQuantidade, float mValor,ProdutoModelo produtoModelo) {
        this.mCodPedido = mCodPedido;
        this.mQuantidade = mQuantidade;
        this.mValor = mValor;
        this.mProduto = produtoModelo;
    }

    public PedidoModelo(int mQuantidade, float mValor,ProdutoModelo produtoModelo) {
        this.mQuantidade = mQuantidade;
        this.mValor = mValor;
        this.mProduto = produtoModelo;
    }

    protected PedidoModelo(Parcel in) {
        mCodPedido = in.readInt();
        mQuantidade = in.readInt();
        mValor = in.readFloat();
    }

    public static final Creator<PedidoModelo> CREATOR = new Creator<PedidoModelo>() {
        @Override
        public PedidoModelo createFromParcel(Parcel in) {
            return new PedidoModelo(in);
        }

        @Override
        public PedidoModelo[] newArray(int size) {
            return new PedidoModelo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCodPedido);
        dest.writeInt(mQuantidade);
        dest.writeFloat(mValor);
    }

    public int getmCodPedido() {
        return mCodPedido;
    }

    public int getmQuantidade() {
        return mQuantidade;
    }

    public float getmValor() {
        return mValor;
    }

    public ProdutoModelo getmProduto() {
        return mProduto;
    }

    public void setmProduto(ProdutoModelo mProduto) {
        this.mProduto = mProduto;
    }
}
