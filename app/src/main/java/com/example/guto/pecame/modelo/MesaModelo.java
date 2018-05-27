package com.example.guto.pecame.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class MesaModelo implements Parcelable{
    private int mCodMesa;
    private boolean mStatus;

    public MesaModelo() {
    }

    public MesaModelo(int mCodMesa, boolean mStatus) {
        this.mCodMesa = mCodMesa;
        this.mStatus = mStatus;
    }

    protected MesaModelo(Parcel in) {
        mCodMesa = in.readInt();
        mStatus = in.readByte() != 0;
    }

    public static final Creator<MesaModelo> CREATOR = new Creator<MesaModelo>() {
        @Override
        public MesaModelo createFromParcel(Parcel in) {
            return new MesaModelo(in);
        }

        @Override
        public MesaModelo[] newArray(int size) {
            return new MesaModelo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCodMesa);
        dest.writeByte((byte) (mStatus ? 1 : 0));
    }

    public int getmCodMesa() {
        return mCodMesa;
    }

    public boolean ismStatus() {
        return mStatus;
    }

    public void setmCodMesa(int mCodMesa) {
        this.mCodMesa = mCodMesa;
    }

    public void setmStatus(boolean mStatus) {
        this.mStatus = mStatus;
    }
}
