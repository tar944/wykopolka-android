package pl.hypeapp.wykopolka.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.hypeapp.wykopolka.R;
import pl.hypeapp.wykopolka.model.Book;

public class BooksRecyclerAdapter extends RecyclerView.Adapter<BooksRecyclerAdapter.BooksRecyclerHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Book> mDataSet = Collections.emptyList();
    private Context mContext;
    private static final String WYKOPOLKA_IMG_HOST = "http://192.168.1.10/wykopolka/public/";

    public BooksRecyclerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public BooksRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.book_row, parent, false);
        return new BooksRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksRecyclerHolder holder, int position) {
        Book current = mDataSet.get(position);
        holder.bookTitle.setText(current.getTitle());
        holder.bookAuthor.setText(current.getAuthor());
        Glide.with(mContext)
                .load(WYKOPOLKA_IMG_HOST + current.getCover())
                .thumbnail(0.3f)
                .into(holder.bookThumbnail);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<Book> books) {
        mDataSet = books;
        notifyDataSetChanged();
    }

    class BooksRecyclerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_book_title)
        TextView bookTitle;
        @BindView(R.id.tv_book_author)
        TextView bookAuthor;
        @BindView(R.id.iv_book_thumbnail)
        ImageView bookThumbnail;

        BooksRecyclerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
