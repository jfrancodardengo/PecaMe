package com.example.guto.pecame.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DetalheProdutoModelo implements Parcelable{
    private int mCodDetalheProdutoModelo;
    private String mObservacao;
    private List<ProdutoModelo> mProdutos;
    private MesaModelo mMesa;

    public DetalheProdutoModelo() {
    }

    public DetalheProdutoModelo(int mCodDetalheProdutoModelo, String mObservacao, List<ProdutoModelo> mProdutos, MesaModelo mMesa) {
        this.mCodDetalheProdutoModelo = mCodDetalheProdutoModelo;
        this.mObservacao = mObservacao;
        this.mProdutos = mProdutos;
        this.mMesa = mMesa;
    }

    protected DetalheProdutoModelo(Parcel in) {
        mCodDetalheProdutoModelo = in.readInt();
        mObservacao = in.readString();
        mProdutos = in.createTypedArrayList(ProdutoModelo.CREATOR);
        mMesa = in.readParcelable(MesaModelo.class.getClassLoader());
    }

    public static final Creator<DetalheProdutoModelo> CREATOR = new Creator<DetalheProdutoModelo>() {
        @Override
        public DetalheProdutoModelo createFromParcel(Parcel in) {
            return new DetalheProdutoModelo(in);
        }

        @Override
        public DetalheProdutoModelo[] newArray(int size) {
            return new DetalheProdutoModelo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCodDetalheProdutoModelo);
        dest.writeString(mObservacao);
        dest.writeTypedList(mProdutos);
        dest.writeParcelable(mMesa, flags);
    }

    public int getmCodDetalheProdutoModelo() {
        return mCodDetalheProdutoModelo;
    }

    public String getmObservacao() {
        return mObservacao;
    }

    public List<ProdutoModelo> getmProdutos() {
        return mProdutos;
    }

    public MesaModelo getmMesa() {
        return mMesa;
    }
}
