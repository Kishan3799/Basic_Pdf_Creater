package com.kishan.basicpdfcreater



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kishan.basicpdfcreater.databinding.ActivityPdfTemplatePreviewBinding
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.kernel.pdf.PdfDocument
import java.io.File
import java.io.FileOutputStream

class PdfTemplatePreview : AppCompatActivity() {
    private lateinit var binding: ActivityPdfTemplatePreviewBinding
    private  var name: String? = null
    private  var age: String? = null
    private  var rollNo: String? = null
    private  var branch: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfTemplatePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.getStringExtra(MainActivity.NAME)
        age = intent.getStringExtra(MainActivity.AGE)
        rollNo = intent.getStringExtra(MainActivity.ROLL_NO)
        branch = intent.getStringExtra(MainActivity.BRANCH)

        binding.tvName.text = name
        binding.tvAge.text = age
        binding.tvEnrollNo.text = rollNo
        binding.tvBranch.text = branch

        binding.createPdfBtn.setOnClickListener {
            Log.d("CreateBtn", "Button is Click")

            createPdf(name, age,rollNo,branch)

        }

    }

    private fun createPdf(name: String?, age: String?, rollNo: String?, branch: String?) {
        //first create Path to store pdf file in download directory
        val pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
        //create file with name
        val file = File(pdfPath,"myFirstPdf.pdf")
        //output the file
        val outputStream = FileOutputStream(file)

        //pdf writer to create output stream
        val writer = PdfWriter(outputStream)
        //pdf document in writing mode
        val pdfDocument = PdfDocument(writer)
        //creating document from pdf document
        val document = Document(pdfDocument)

        //set the default size of page
        pdfDocument.defaultPageSize
        //set the margins of page
        document.setMargins(10f,10f,10f,10f)

        //set the alignment on content in center
        document.setTextAlignment(TextAlignment.CENTER)

        //adding content on the document
        document.add(Paragraph("Name :      $name" ))
        document.add(Paragraph("Age  :      $age" ))
        document.add(Paragraph("Roll No :      $rollNo" ))
        document.add(Paragraph("Branch :      $branch" ))

        //closing the document
        document.close()

        Toast.makeText(applicationContext,"File is Generated" ,Toast.LENGTH_SHORT).show()

    }


}