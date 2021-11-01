package com.example.books_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.books_app.R;
import com.example.books_app.models.Book;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    Context context;
    ArrayList<Book> arrBook;

    public BookAdapter(Context context, ArrayList<Book> arrBook) {
        this.context = context;
        this.arrBook = arrBook;
    }
    @Override
    public int getCount() {
        return arrBook.size();
    }

    @Override
    public Object getItem(int i) {
        return arrBook.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        ImageView imgBook;
        TextView txtTitle,txtPrice;
        RatingBar rbStar;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            viewHolder = new BookAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_listview_book, null);
            viewHolder.imgBook = convertView.findViewById(R.id.imgBook);
            viewHolder.txtTitle = convertView.findViewById(R.id.txtTitle);
            viewHolder.txtPrice = convertView.findViewById(R.id.txtPrice);
            viewHolder.rbStar = convertView.findViewById(R.id.rbStar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BookAdapter.ViewHolder) convertView.getTag();
            //Toast.makeText(context,"Đã load hết sản phẩm",Toast.LENGTH_SHORT).show();
        }

        Book book = (Book) getItem(i);
        viewHolder.txtTitle.setText(book.getTitle());

        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText(decimalFormat.format(book.getPrice()) +"đ");

//        viewHolder.txtDesLaptop.setMaxLines(2);
//        viewHolder.txtDesLaptop.setEllipsize(TextUtils.TruncateAt.END);
//        viewHolder.txtDesLaptop.setText(sanPham.getDesSanPham());
        Picasso.get().load(book.getImage()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHolder.imgBook);
        return convertView;
    }
}
