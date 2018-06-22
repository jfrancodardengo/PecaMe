package com.example.guto.pecame.adaptadores;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guto.pecame.fragmentos.HamburguerFragment;
import com.example.guto.pecame.ui.ListaProdutoActivity;
import com.example.guto.pecame.utils.AdapterCallback;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProdutoAdaptador extends RecyclerView.Adapter<ProdutoAdaptador.ProductViewHolder> {
    public static final String PRODUTO_PARCELABLE = "produto_parcelable";

    private List<ProdutoModelo> mProdutoModeloList;
    ListaProdutoActivity listaProdutoActivity = new ListaProdutoActivity();
    private static List mRecipeDrawables;
    private ProdutoModelo mProdutoModelo;
    private Context mContext;
    private AdapterCallback mAdapterCallback;
    private HamburguerFragment mFragment;

    public ProdutoAdaptador(List<ProdutoModelo> mProdutoModeloList, AdapterCallback adapterCallback, int fragment) {
        this.mProdutoModeloList = mProdutoModeloList;
        this.mAdapterCallback = adapterCallback;
//        this.mFragment = fragment;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ProductViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        int idDrawable;
        Bitmap imagemBitmap;
        mProdutoModelo = mProdutoModeloList.get(position);
        holder.textProduct.setText(mProdutoModelo.getmDescProduto());
        holder.textPrice.setText(mProdutoModelo.getmPreco());
        holder.editObservacao.setText(mProdutoModelo.getmObservacao());

//        mFragment.getTargetFragment().equals("HamburguerFragment")
//        mFragment.get

        if(mFragment.getTag().toString().equals("HamburguerFragment")){
//        if(){
            try {
                mRecipeDrawables = listaProdutoActivity.chamarAsyncTask();
                idDrawable = (Integer) mRecipeDrawables.get(0);
                imagemBitmap = BitmapFactory.decodeResource(Resources.getSystem(),idDrawable);
                holder.imageCardView.setImageBitmap(imagemBitmap);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        holder.checkItem.setChecked(mProdutoModelo.isSelected());
        holder.checkItem.setTag(mProdutoModelo);

        holder.checkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                ProdutoModelo produto = (ProdutoModelo) cb.getTag();
                produto.setSelected(cb.isChecked());
                mAdapterCallback.onCheckItemCallback(produto,cb.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProdutoModeloList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_product)
        TextView textProduct;
        @BindView(R.id.text_price)
        TextView textPrice;
        @BindView(R.id.check_item)
        CheckBox checkItem;
        @BindView(R.id.edit_observacao)
        EditText editObservacao;
        @BindView(R.id.img_thumbnail_cardview)
        ImageView imageCardView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
