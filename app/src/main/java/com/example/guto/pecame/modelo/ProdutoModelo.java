package com.example.guto.pecame.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GUTO on 02/05/2018.
 */

public class ProdutoModelo implements Parcelable{
    private int mCodProduto;
    private String mDescProduto;
    private String mPreco;
    private String mObservacao;
    private boolean isSelected;

    public ProdutoModelo(String mDescProduto, String mPreco,String mObservacao) {
        this.mDescProduto = mDescProduto;
        this.mPreco = mPreco;
        this.mObservacao = mObservacao;
    }

    private ProdutoModelo(Parcel in) {
        mCodProduto = in.readInt();
        mDescProduto = in.readString();
        mPreco = in.readString();
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

    public String getmObservacao() {
        return mObservacao;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setmCodProduto(int mCodProduto) {
        this.mCodProduto = mCodProduto;
    }
}
