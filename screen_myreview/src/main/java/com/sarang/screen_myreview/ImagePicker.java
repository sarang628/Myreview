package com.sarang.screen_myreview;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ImagePicker {
    private static ImagePicker imagePicker;
    private OnImageSelectListener onImageSelectListener;

    public interface OnImageSelectListener {
        void onImageSelect(String path);
    }

    public static ImagePicker getInstance() {
        if (imagePicker == null)
            imagePicker = new ImagePicker();
        return imagePicker;
    }

    private void ImagePicker() {

    }

    public static final int PICK_IMAGE = 1;

    public void pickPictures(Activity activity, OnImageSelectListener onImageSelectListener) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        this.onImageSelectListener = onImageSelectListener;
    }

    public void pickPictures(Fragment fragment, OnImageSelectListener onImageSelectListener) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        fragment.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        this.onImageSelectListener = onImageSelectListener;
    }

    public void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            ArrayList<String> imagesEncodedList = new ArrayList<>();
            if (data.getData() != null) {
                Uri mImageUri = data.getData();
                try {
                    String path = getRealPathFromURI(context, mImageUri);
                    if (onImageSelectListener != null && path != null)
                        onImageSelectListener.onImageSelect(path);
                } catch (Exception e) {

                }
            }
            else {
                ClipData clipDate = data.getClipData();
                ArrayList<Uri> mArrayUri = new ArrayList<>();
                for (int i = 0; i < clipDate.getItemCount(); i++) {

                    ClipData.Item item = clipDate.getItemAt(i);
                    Uri uri = item.getUri();
                    mArrayUri.add(uri);
                    // Get the cursor
                    Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imageEncoded = cursor.getString(columnIndex);
                    imagesEncodedList.add(imageEncoded);
                    for (String path : imagesEncodedList) {
                        if (onImageSelectListener != null)
                            onImageSelectListener.onImageSelect(path);
                    }
                    cursor.close();
                }
            }
        }
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {

        // can post image
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri,
                proj, // Which columns to return
                null,       // WHERE clause; which rows to return (all rows)
                null,       // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    public static Bitmap uriExif(Context context, Uri uri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            ExifInterface exif = new ExifInterface(inputStream);
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int rotationInDegrees = exifToDegrees(rotation);

            Matrix matrix = new Matrix();
            if (rotation != 0) {matrix.preRotate(rotationInDegrees);}

            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap uriExif(String path) {
        try {
            ExifInterface exif = new ExifInterface(path);
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int rotationInDegrees = exifToDegrees(rotation);

            Matrix matrix = new Matrix();
            if (rotation != 0) {matrix.preRotate(rotationInDegrees);}

            Bitmap bitmap = BitmapFactory.decodeFile(path);

            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) { return 90; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) { return 180; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) { return 270; }
        return 0;
    }
}
