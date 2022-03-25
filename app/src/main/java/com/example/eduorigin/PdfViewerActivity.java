package com.example.eduorigin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.widget.Toast;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;
import java.io.File;

public class PdfViewerActivity extends AppCompatActivity implements OnLoadCompleteListener, OnPageChangeListener {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        progressDialog =new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Wait While Loading.....");
        progressDialog.show();

          final PDFView pdfView=findViewById(R.id.pdfView);
          String url=getIntent().getStringExtra("pdf");
          String pdfurl="http://192.168.0.104/EduOriginAPI/Registration/pdfs/"+url;
         FileLoader.with(this).load(pdfurl,false).fromDirectory("MY_PDFs",FileLoader.DIR_INTERNAL).asFile(new FileRequestListener<File>() {
             @Override
             public void onLoad(FileLoadRequest request, FileResponse<File> response) {

                 File pdffile=response.getBody();
                 try {
                     pdfView.fromFile(pdffile)
                             .defaultPage(1)
                             .enableAnnotationRendering(true)
                             .onLoad(PdfViewerActivity.this)
                             .scrollHandle(new DefaultScrollHandle(PdfViewerActivity.this))
                             .spacing(10).load();

                 }catch(Exception e){}
             }
             @Override
             public void onError(FileLoadRequest request, Throwable t) {
                 Toast.makeText(PdfViewerActivity.this, "error", Toast.LENGTH_SHORT).show();
             }
         });
    }
    @Override
    public void loadComplete(int nbPages) {

        progressDialog.dismiss();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        Toast.makeText(this, "Load Error", Toast.LENGTH_SHORT).show();
    }
}