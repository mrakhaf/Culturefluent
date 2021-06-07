package id.co.culturefluent.utils

import id.co.culturefluent.R
import id.co.culturefluent.data.Category
import id.co.culturefluent.data.InstrumentModel
import kotlinx.android.synthetic.main.fragment_classifyobject.view.*

object Data {
    fun getInstrumentList(): List<InstrumentModel> {
        return instruments
    }

    fun getInstrumentByName(name:String): InstrumentModel {
        for(i in instruments){
            if(i.name.toLowerCase().replace(" ", "") == name.toLowerCase().replace(" ", "")){
                return i
            }
        }
        return InstrumentModel(R.drawable.bg_home,"","","", listOf("2"),1)
    }

    private val category = listOf("Angklung","Karinding","Kecapi","Kendang","Rebab","Suling")

    fun getListCategory(probs:List<Double>):List<Category>{
        var cats = ArrayList<Category>()
        for(i in probs.indices){
            var cat = Category(category[i],probs[i])
            cats.add(cat)
        }
        return cats
    }

    private val instruments: List<InstrumentModel> = listOf(
        InstrumentModel(
            R.drawable.bg_home,
            "Angklung",
            "Angklung merupakan alat musik tradisional Jawa Barat yang terbuat dari potongan bambu. Alat musik ini terdiri dari 2 sampai 4 tabung bambu yang dirangkai menjadi satu dengan tali rotan. Tabung bambu diukir detail dan dipotong sedemikian rupa untuk menghasilkan nada tertentu ketika bingkai bambu digoyang.\n" +
                    "\tKata \u0089Û÷angklung\u0089Ûª sendiri berasal dari bahasa Sunda \u0089Û÷angkleung-angkleungan\u0089Ûª yaitu gerakan pemain angklung, serta dari suara \u0089Û÷klung\u0089Ûª yang dihasilkan instrumen bambu ini. Angklung sebenarnya merupakan pengembangan dari alat musik calung, yaitu tabung bambu yang dipukul. Sementara, angklung merupakan tabung bambu yang digoyang sehingga menghasilkan hanya satu nada untuk setiap instrumennya.\n" +
                    "Seiring perkembangan angklung, sejak November 2010, UNESCO telah menetapkan angklung sebagai Karya Agung Warisan Budaya Lisan dan Nonbendawi Manusia. Salah satu tempat yang masih melestarikan kebudayaan angklung adalah Saung Udjo. Di sanggar yang terletak di Kota Bandung ini, pengunjung tidak hanya dapat melihat berbagai jenis angklung, tapi juga belajar proses pembuatan angklung. (sumber:IndonesiaKaya)\n" +
                    "\n",
            "question",
            listOf("a", "b", "c", "d"),
            1
        ),
        InstrumentModel(R.drawable.bg_home,
            "Karinding",
            "Karinding adalah alat seni tradisional Sunda berupa bilahan kecil yang memanfaatkan resonator rongga mulut untuk menghasilkan bunyi dengung. Fungsi awalnya merupakan alat pengusir rasa bosan para petani pada saat menunggu padi di sawah dari serangga atau burung pemakan padi. Perkembangan berikutnya adalah sebagai fungsi sosial, yaitu  sebagai salah satu bagian dari kekayaan alat musik tradisional masyarakat Sunda. karinding telah bermetamorfose/ bersalin rupa menjadi bagian dari alat seni yang mandiri dengan ke khasan suaranya. (Sumber: kebudayaan.kemendikbud.go.id)",
            "question",
            listOf("a", "b", "c", "d"),
            1
        ),
        InstrumentModel(R.drawable.bg_home,
            "Kecapi",
            "Dimainkan dengan cara dipetik, Kecapi adalah alat musik tradisional khas Jawa Barat yang terbagi menjadi dua jenis bagian.Kecapi Indung atau kecapi induk yang memiliki ukuran lebih besar dan berdawai atau memiliki 18 hingga 20 senar digunakan sebagai instrumen untuk memimpin jalannya musik seperti memberikan intro hingga menentukan tempo. Sedangkan Kecapi Rincik atau kecapi anak dimainkan sebagai iringan musik dengan mengisi jeda-jeda antar nada dengan frekuensi tinggi.\n",
            "question",
            listOf("a", "b", "c", "d"),
            1
        ),
        InstrumentModel(R.drawable.bg_home,
            "Kendang",
            "Kendang, kendhang, atau biasa disebut juga dengan gendang merupakan alat musik yang dimainkan dengan cara di pukul. Biasa dimainkan dengan tangan atau dengan alat pemukul gendang. Hampir semua daerah di Indonesia memiliki gendang dengan ciri khas masing-masing. Kendang juga merupakan salah satu instrumen dalam gamelan Jawa Tengah yang salah satu fungsi utamanya mengatur irama.\n" +
                    "\n" +
                    "Berikut jenis-jenis kendang :\n" +
                    "a. Kendang yang kecil disebut ketipung\n" +
                    "b. Kendang sedang disebut kendang ciblon/kebar.\n" +
                    "c. Pasangan ketipung ada satu lagi bernama kendang gedhe biasa disebut kendang kalih.\n",
            "question",
            listOf("a", "b", "c", "d"),
            1
        ),
        InstrumentModel(R.drawable.bg_home,
            "Rebab",
            "Rebab adalah salah satu alat musik melodi yang banyak digunakan dalam kesenian Melayu, khususnya Sumatera, Jawa, dan Kalimantan. Rebab tergolong instrument “kordofon” dimana sumber bunyinya berasal dari dawai atau senar yang dimainkan dengan cara digesek. Dalam kesenian Betawi alat musik rebab menjadi salah satu instrument pengiring pertunjukan Wayang Kulit dan Topeng Betawi. Rebab juga menjadi bagian dari ansambel musik gamelan dan karawitan Jawa yang biasanya berfungsi sebagai penghias gending dan menuntun arah lagu sinden. Rebab dapat menyelaraskan cengkok nada dengan mengintepretasikan irama gamelan, sinden, maupun wiraswara (penyanyi laki-laki).\n",
            "question",
            listOf("a", "b", "c", "d"),
            1
        ),
        InstrumentModel(R.drawable.bg_home,
            "Suling",
            "Alat musik suling berasal dari daerah Jawa Barat. Instrumen musik yang terbuat dari bahan bambu ini tergolong ke dalam jenis instrumen musik tiup atau biasa dikenal sebagai aerophone. Gawai ini memiliki bentuk yang kecil dan memanjang sekitar 30 cm dengan garis tengah sebesar 3 cm. Ciri khas sebuah suling adalah adanya lubang kecil pada bagian tubuhnya yang berfungsi sebagai tempat mengatur nada yang diinginkan oleh pemainnya.\n" +
                    "Suling di Indonesia umumnya menggunakan do, re, mi, fa, sol, la, si sebagai tangga nadanya. Di Indonesia sendiri, instrumen musik suling yang dimainkan dengan cara ditiup ini sangat populer dan dikenal sebagai pengiring musik dangdut, yaitu musik yang asli berasal dari Indonesia.Suara dari alat musik tiup yang terbuat dari bambu ini pun dapat menghasilkan suara yang meliuk sehingga sangat cocok dijadikan sebagai iringan musik dangdut yang identik dengan liukan nada atau cengkok.\n",
            "question",
            listOf("a", "b", "c", "d"),
            1
        )
    )

}