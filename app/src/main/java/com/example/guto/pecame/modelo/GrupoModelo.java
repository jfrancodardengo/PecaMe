package com.example.guto.pecame.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GrupoModelo implements Parcelable{
    private int mCodGrupo;
    private String mDescGrupo;
    private List<SubgrupoModelo> mSubgrupos;

    public GrupoModelo() {
    }

    public GrupoModelo(int mCodGrupo, String mDescGrupo, List<SubgrupoModelo> mSubgrupos) {
        this.mCodGrupo = mCodGrupo;
        this.mDescGrupo = mDescGrupo;
        this.mSubgrupos = mSubgrupos;
    }

    protected GrupoModelo(Parcel in) {
        mCodGrupo = in.readInt();
        mDescGrupo = in.readString();
        mSubgrupos = in.createTypedArrayList(SubgrupoModelo.CREATOR);
    }

    public static final Creator<GrupoModelo> CREATOR = new Creator<GrupoModelo>() {
        @Override
        public GrupoModelo createFromParcel(Parcel in) {
            return new GrupoModelo(in);
        }

        @Override
        public GrupoModelo[] newArray(int size) {
            return new GrupoModelo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCodGrupo);
        dest.writeString(mDescGrupo);
        dest.writeTypedList(mSubgrupos);
    }

    public int getmCodGrupo() {
        return mCodGrupo;
    }

    public String getmDescGrupo() {
        return mDescGrupo;
    }

    public List<SubgrupoModelo> getmSubgrupos() {
        return mSubgrupos;
    }
}
