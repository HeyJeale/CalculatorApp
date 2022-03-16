package edu.zjut.calculator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ImageButtonWithText extends ConstraintLayout {

    private ImageButton imageButton;

    private TextView textView;

    public ImageButtonWithText(@NonNull Context context) {
        super(context);
    }

    public ImageButtonWithText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.image_button_with_text, this,true);
        imageButton=findViewById(R.id.image);
        textView=findViewById(R.id.text);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ImageButtonWithText);
        textView.setText(typedArray.getText(R.styleable.ImageButtonWithText_android_text));
        textView.setTextSize(typedArray.getFloat(R.styleable.ImageButtonWithText_textSize,R.styleable.ImageButtonWithText_textSize));
        textView.setTextColor(typedArray.getColor(R.styleable.ImageButtonWithText_textColor,R.styleable.ImageButtonWithText_textColor));
        Typeface typeface=Typeface.createFromAsset(context.getAssets(), "s.ttf");
        textView.setTypeface(typeface);
        imageButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageButton.setImageDrawable(typedArray.getDrawable(R.styleable.ImageButtonWithText_src));
        typedArray.recycle();
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setOnClickListener(OnClickListener onClickListener){
        getImageButton().setOnClickListener(onClickListener);
    }
}
