package com.example.guto.pecame.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by GUTO on 02/05/2018.
 */

public class ProdutoModelo implements Parcelable{
    private int mCodProduto;
    private String mDescProduto;
    private String mPreco;
    private List<GrupoModelo> mGrupos;
    private String mObservacao;

    public ProdutoModelo() {
    }

    public ProdutoModelo(String mDescProduto, String mPreco) {
        this.mDescProduto = mDescProduto;
        this.mPreco = mPreco;
    }

    public ProdutoModelo(int mCodProduto, String mDescProduto, String mPreco, List<GrupoModelo> mGrupos,String mObservacao) {
        this.mCodProduto = mCodProduto;
        this.mDescProduto = mDescProduto;
        this.mPreco = mPreco;
        this.mGrupos = mGrupos;
        this.mObservacao = mObservacao;
    }

    protected ProdutoModelo(Parcel in) {
        mCodProduto = in.readInt();
        mDescProduto = in.readString();
        mPreco = in.readString();
        mGrupos = in.createTypedArrayList(GrupoModelo.CREATOR);
        mObservacao = in.readString();
    }

    public static final Creator<ProdutoModelo> CREATOR = new Creator<ProdutoModelo>() {
        @Override
        public ProdutoModelo createFromParcel(Parcel in) {
            return new ProdutoModelo(in);
        }

        @Override
        public ProdutoModelo[] newArray(int size) {
            return new ProdutoModelo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCodProduto);
        dest.writeString(mDescProduto);
        dest.writeString(mPreco);
        dest.writeTypedList(mGrupos);
        dest.writeString(mObservacao);
    }

    public int getmCodProduto() {
        return mCodProduto;
    }

    public String getmDescProduto() {
        return mDescProduto;
    }

    public String getmPreco() {
        return mPreco;
    }

    public List<GrupoModelo> getmGrupos() {
        return mGrupos;
    }

    public String getmObservacao() {
        return mObservacao;
    }

    public void setmCodProduto(int mCodProduto) {
        this.mCodProduto = mCodProduto;
    }
}
