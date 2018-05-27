package com.example.guto.pecame.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class SubgrupoModelo implements Parcelable {

    private int mCodSubgrupo;
    private String mDescSubgrupo;

    public SubgrupoModelo() {
    }

    public SubgrupoModelo(int mCodSubgrupo, String mDescSubgrupo) {
        this.mCodSubgrupo = mCodSubgrupo;
        this.mDescSubgrupo = mDescSubgrupo;
    }


    protected SubgrupoModelo(Parcel in) {
        mCodSubgrupo = in.readInt();
        mDescSubgrupo = in.readString();
    }

    public static final Creator<SubgrupoModelo> CREATOR = new Creator<SubgrupoModelo>() {
        @Override
        public SubgrupoModelo createFromParcel(Parcel in) {
            return new SubgrupoModelo(in);
        }

        @Override
        public SubgrupoModelo[] newArray(int size) {
            return new SubgrupoModelo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCodSubgrupo);
        dest.writeString(mDescSubgrupo);
    }

    public int getmCodSubgrupo() {
        return mCodSubgrupo;
    }

    public String getmDescSubgrupo() {
        return mDescSubgrupo;
    }
}
