package com.example.guto.pecame.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PedidoModelo implements Parcelable{
    private int mCodPedido;
    private int mQuantidade;
    private String mValor;
    private List<DetalheProdutoModelo> mItensProdutos;

    public PedidoModelo() {
    }

    public PedidoModelo(int mCodPedido, int mQuantidade, String mValor, List<DetalheProdutoModelo> mItensProdutos) {
        this.mCodPedido = mCodPedido;
        this.mQuantidade = mQuantidade;
        this.mValor = mValor;
        this.mItensProdutos = mItensProdutos;
    }

    protected PedidoModelo(Parcel in) {
        mCodPedido = in.readInt();
        mQuantidade = in.readInt();
        mValor = in.readString();
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
        dest.writeString(mValor);
    }

    public int getmCodPedido() {
        return mCodPedido;
    }

    public int getmQuantidade() {
        return mQuantidade;
    }

    public String getmValor() {
        return mValor;
    }

    public List<DetalheProdutoModelo> getmItensProdutos() {
        return mItensProdutos;
    }
}
