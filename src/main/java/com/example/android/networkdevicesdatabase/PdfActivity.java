package com.example.android.networkdevicesdatabase;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.networkdevicesdatabase.Data.Device;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PdfActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

    private static final String TAG = PdfActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "android_tutorial.pdf";
    PDFView pdfView;
    Integer pageNumber = 0;
    public static String pdfFileName;
    public static String mPdfUrl;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    Context mContext = this;
    @BindView(R.id.no_file)
    TextView noFile;
    @BindView(R.id.no_internet)
    TextView noInternet;
    private Device device;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        ButterKnife.bind(this);

        Intent intentThatStartedThisActivity = getIntent();
        mPdfUrl = device.getPdfUrl();

       /* if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("pdfUrl")) {
                mPdfUrl = intentThatStartedThisActivity.getStringExtra("pdfUrl");
            }
        }*/

        pdfView = (PDFView) findViewById(R.id.pdfView);

        ConnectivityManager connMgr = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (!mPdfUrl.equals("") && (networkInfo != null && networkInfo.isConnected())) {
            noFile.setVisibility(View.GONE);
            noInternet.setVisibility(View.GONE);
            if (ActivityCompat.checkSelfPermission(PdfActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(PdfActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(PdfActivity.this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                downloadFile();
            }
        } else if (mPdfUrl.equals("") && (networkInfo != null && networkInfo.isConnected())) {
            pdfView.setVisibility(View.GONE);
            noFile.setVisibility(View.VISIBLE);
            noInternet.setVisibility(View.GONE);
            noFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }else {
            pdfView.setVisibility(View.GONE);
            noFile.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
            noInternet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                downloadFile();
                break;
        }
    }

    private void downloadFile() {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(mPdfUrl);

        try {
            // showProgressDialog("Download File", "Downloading File...");
            final File localFile = File.createTempFile("pdf_file", "pdf");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    File pdfFile = new File(localFile.getAbsolutePath());


                    if (pdfFile.exists()) {
                        pdfView.fromFile(pdfFile)
                                //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                                .enableSwipe(true)
                                .swipeHorizontal(false)
                                .enableDoubletap(true)
                                .defaultPage(0)
                                .enableAnnotationRendering(true)
                                .onPageChange((OnPageChangeListener) mContext)
                                .enableAnnotationRendering(true)
                                .onLoad((OnLoadCompleteListener) mContext)
                                .password(null)
                                .scrollHandle(new DefaultScrollHandle(mContext))
                                .onLoad(new OnLoadCompleteListener() {
                                    @Override
                                    public void loadComplete(int nbPages) {
                                        pdfView.setMinZoom(1f);
                                        pdfView.setMidZoom(5f);
                                        pdfView.setMaxZoom(10f);
                                        pdfView.zoomTo(2f);
                                        pdfView.scrollTo(100, 0);
                                        pdfView.moveTo(0f, 0f);
                                    }
                                })
                                .load();

                    }
                }


            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    //  dismissProgressDialog();
                    //   showToast("Download Failed!");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Main", "IOE Exception");
        }
    }


    /*private void displayPdf(String assetFileName) {
        pdfFileName = assetFileName;

        //pdfView.fromAsset(SAMPLE_FILE)
        Uri pdf = Uri.parse(mPdfUrl);
        pdfView.fromUri(pdf)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }*/

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        //setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // click on 'up' button in the action bar, handle it here
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else
            super.onBackPressed();

    }
}
