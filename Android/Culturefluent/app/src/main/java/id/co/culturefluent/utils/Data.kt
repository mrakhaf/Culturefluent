package id.co.culturefluent.utils

import id.co.culturefluent.R
import id.co.culturefluent.data.Category
import id.co.culturefluent.data.InstrumentModel

object Data {
    fun getInstrumentList(): List<InstrumentModel> {
        return instruments
    }

    fun getInstrumentByName(name: String): InstrumentModel {
        for (i in instruments) {
            if (i.name.toLowerCase().replace(" ", "") == name.toLowerCase().replace(" ", "")) {
                return i
            }
        }
        return InstrumentModel(R.drawable.bg_home, "", "", "", listOf("2"), 1)
    }

    private val category = listOf("Angklung", "Karinding", "Kecapi", "Kendang", "Rebab", "Suling")

    fun getListCategory(probs: List<Double>): List<Category> {
        var cats = ArrayList<Category>()
        for (i in probs.indices) {
            var cat = Category(category[i], probs[i])
            cats.add(cat)
        }
        return cats
    }

    private val instruments: List<InstrumentModel> = listOf(
        InstrumentModel(
            R.drawable.a_angklung_,
            "Angklung",
            "\t\t\tAngklung merupakan alat musik tradisional Jawa Barat yang terbuat dari potongan bambu. " +
                    "Alat musik ini terdiri dari 2 sampai 4 tabung bambu yang dirangkai menjadi satu dengan tali rotan. " +
                    "Tabung bambu diukir detail dan dipotong sedemikian rupa untuk menghasilkan nada tertentu " +
                    "ketika bingkai bambu digoyang.\n\n" +
                    "\t\t\tKata “angklung” sendiri berasal dari bahasa Sunda “angkleung-angkleungan” " +
                    "yaitu gerakan pemain angklung, serta dari suara “klung” yang dihasilkan instrumen bambu ini. " +
                    "Angklung sebenarnya merupakan pengembangan dari alat musik calung, yaitu tabung bambu yang dipukul. " +
                    "Sementara, angklung merupakan tabung bambu yang digoyang sehingga menghasilkan hanya satu nada untuk setiap instrumennya.\n\n" +
                    "\t\t\tSeiring perkembangan angklung, sejak November 2010, UNESCO telah menetapkan angklung " +
                    "sebagai Karya Agung Warisan Budaya Lisan dan Nonbendawi Manusia. " +
                    "Salah satu tempat yang masih melestarikan kebudayaan angklung adalah Saung Udjo. " +
                    "Di sanggar yang terletak di Kota Bandung ini, pengunjung tidak hanya dapat melihat " +
                    "berbagai jenis angklung, tapi juga belajar proses pembuatan angklung. \n\n",
            "Apa nama alat musik berupa tabung bambu yang merupakan ide dasar dari alat musik angklung?",
            listOf("Klung", "Udjo", "Calung", "Angkleungan"),
            0
        ),
        InstrumentModel(
            R.drawable.a_karinding,
            "Karinding",
            "\t\t\tKarinding adalah alat seni tradisional Sunda berupa bilahan kecil yang memanfaatkan resonator rongga " +
                    "mulut untuk menghasilkan bunyi dengung. Fungsi awalnya merupakan alat pengusir rasa bosan para petani " +
                    "pada saat menunggu padi di sawah dari serangga atau burung pemakan padi. Perkembangan berikutnya adalah " +
                    "sebagai fungsi sosial, yaitu  sebagai salah satu bagian dari kekayaan alat musik tradisional masyarakat " +
                    "Sunda. karinding telah bermetamorfosa/ bersalin rupa menjadi bagian dari alat seni yang mandiri " +
                    "dengan kekhasan suaranya."+"\n\n \t\t\t"+
                    "Bahan untuk membuat karinding ada dua alternatif bahan baku yaitu bambu dan pelepah pohon aren. " +
                    "Cara memainkan karinding cukup dengan menempelkan ruas tengah karinding lalu ditiup " +
                    "dan dipukul dengan jari tangan.",
            "Apa fungsi awal pembuatan karinding?",
            listOf("Mengusir serangga atau burung pemakan padi", "Mengusir hujan saat musim panen",
                "Mengusir rasa bosan saat menunggu sawah", "Mengusir rasa malas saat di ladang"),
            2
        ),
        InstrumentModel(
            R.drawable.a_kecapi_,
            "Kecapi",
            "\t\t\tDimainkan dengan cara dipetik, Kecapi adalah alat musik tradisional khas Jawa Barat yang terbagi menjadi " +
                    "dua jenis bagian.Kecapi Indung atau kecapi induk yang memiliki ukuran lebih besar dan berdawai " +
                    "atau memiliki 18 hingga 20 senar digunakan sebagai instrumen untuk memimpin jalannya musik " +
                    "seperti memberikan intro hingga menentukan tempo. Sedangkan Kecapi Rincik atau kecapi anak " +
                    "dimainkan sebagai iringan musik dengan mengisi jeda-jeda antar nada dengan frekuensi tinggi.",
            "Berapa jumlah senar yang ada pada kecapi?",
            listOf("10 -12 Senar", "14 -16 Senar", "4 - 6 Senar", "18 - 20 Senar"),
            3
        ),
        InstrumentModel(
            R.drawable.a_kendang_,
            "Kendang",
            "\t\t\tKendang, kendhang, atau biasa disebut juga dengan gendang merupakan alat musik yang dimainkan dengan " +
                    "cara di pukul. Biasa dimainkan dengan tangan atau dengan alat pemukul gendang. " +
                    "Hampir semua daerah di Indonesia memiliki gendang dengan ciri khas masing-masing. " +
                    "Kendang juga merupakan salah satu instrumen dalam gamelan Jawa Tengah yang salah satu " +
                    "fungsi utamanya mengatur irama." +
                    "\n\n" +
                    "Berikut jenis-jenis kendang :\n" +
                    "a. Kendang yang kecil disebut ketipung\n" +
                    "b. Kendang sedang disebut kendang ciblon/kebar.\n" +
                    "c. Pasangan ketipung ada satu lagi bernama kendang gedhe biasa disebut kendang kalih.\n\n"+
                    "\t\t\tDalam pertunjukan tari atau wayang, pemain kendang akan mengikuti gerakan penari, " +
                    "dan mengkomunikasikannya kepada pemain lain dalam ansambel. Di Jawa Barat (Sunda), " +
                    "kendang digunakan untuk menjaga tempo Gamelan Degung. Kendang juga digunakan " +
                    "sebagai instrumen utama tarian Jaipongan . Dalam komposisi lain yang disebut " +
                    "Rampak Kendang, sekelompok penabuh bermain secara harmonis.",
            "Sekelompok penabuh kendang yang bermain secara harmonis, disebut...",
            listOf("Rampak Kendang", "Jaipong", "Wayang", "Ketipung"),
            0
        ),
        InstrumentModel(
            R.drawable.a_rebab,
            "Rebab",
            "\t\t\tRebab adalah salah satu alat musik melodi yang banyak digunakan dalam kesenian Melayu, " +
                    "khususnya Sumatera, Jawa, dan Kalimantan. Rebab tergolong instrumen “kordofon” " +
                    "dimana sumber bunyinya berasal dari dawai atau senar yang dimainkan dengan cara digesek. " +
                    "Dalam kesenian Betawi alat musik rebab menjadi salah satu instrumen pengiring " +
                    "pertunjukan Wayang Kulit dan Topeng Betawi. Rebab juga menjadi bagian dari ansambel " +
                    "musik gamelan dan karawitan Jawa yang biasanya berfungsi sebagai penghias gending " +
                    "dan menuntun arah lagu sinden. Rebab dapat menyelaraskan cengkok nada " +
                    "dengan menginterpretasikan irama gamelan, sinden, maupun wiraswara (penyanyi laki-laki)."+"\n\n\t\t\t"+
                    "Ukuran rebab biasanya kecil, badannya bulat, bagian depan yang tercakup " +
                    "dalam suatu membran seperti perkamen atau kulit domba dan memiliki leher panjang terpasang. " +
                    "Ada leher tipis panjang dengan pegbox pada akhir dan ada satu, dua atau tiga senar. " +
                    "Tidak ada papan nada. Alat musik ini dibuat tegak, baik bertumpu di pangkuan atau di lantai. " +
                    "Busurnya biasanya lebih melengkung daripada biola." +"\n\n\t\t\t"+
                    "Dalam gamelan Indonesia rebab adalah instrumen penting dalam mengelaborasi " +
                    "dan menghiasi melodi dasar. Ini tidak harus sesuai persis dengan skala " +
                    "instrumen gamelan lainnya dan dapat dimainkan dalam waktu yang relatif bebas, " +
                    "penyelesaian frasa setelah dentuman dari gong ageng (gong besar yang \"mengatur\" ansambel). " +
                    "Rebab juga sering memainkan buka yang saat itu adalah bagian dari ansambel.",
            "Alat musik yang biasanya diiringi oleh rebab adalah...",
            listOf("Tifa", "Sape", "Gamelan", "Krombi"),
            2
        ),
        InstrumentModel(
            R.drawable.a_suling_,
            "Suling",
            "\t\t\tAlat musik suling berasal dari daerah Jawa Barat. Instrumen musik yang terbuat dari " +
                    "bahan bambu ini tergolong ke dalam jenis instrumen musik tiup atau biasa dikenal sebagai aerophone. " +
                    "Gawai ini memiliki bentuk yang kecil dan memanjang sekitar 30 cm dengan garis tengah sebesar 3 cm. " +
                    "Ciri khas sebuah suling adalah adanya lubang kecil pada bagian tubuhnya yang berfungsi " +
                    "sebagai tempat mengatur nada yang diinginkan oleh pemainnya."+"\n\n\t\t\t"+
                    "Suling di Indonesia umumnya menggunakan do, re, mi, fa, sol, la, si sebagai tangga nadanya. " +
                    "Di Indonesia sendiri, instrumen musik suling yang dimainkan dengan cara ditiup ini " +
                    "sangat populer dan dikenal sebagai pengiring musik dangdut, yaitu musik yang asli berasal " +
                    "dari Indonesia.Suara dari alat musik tiup yang terbuat dari bambu ini pun dapat " +
                    "menghasilkan suara yang meliuk sehingga sangat cocok dijadikan sebagai iringan musik " +
                    "dangdut yang identik dengan liukan nada atau cengkok."+"\n\n\t\t\t"+
                    "Adapun suling Sunda merupakan alat musik tiup yang terbuat dari jenis awi/bambu kecil, " +
                    "berkulit tipis dan berongga besar, dengan struktur yang terdiri atas sumber " +
                    "(lubang tiup yang terdapat pada bagian atas), suliwer (tali yang melingkar/mengikat sumber), " +
                    "dan beberapa lubang yang berfungsi untuk mengendalikan nada saat ditiup"+"\n\n\t\t\t"+
                    "Biasanya jenis bambu yang digunakan adalah bambu tamiang, bambu buluh dan bambu iraten. " +
                    "Dari ketiga jenis bambu tersebut, bambu tamiang memiliki kulit yang lebih tipis dengan ruas yang panjang, " +
                    "sehingga menjadi bambu yang paling baik untuk dijadikan bahan suling. " +
                    "Sedangkan bambu buluh dan iraten lebih tebal dengan ruas batang yang cenderung pendek sehingga " +
                    "bambu ini seringkali hanya dijadikan bahan baku alternatif.",
            "Bambu yang biasanya digunakan untuk membuat suling adalah...",
            listOf("Bambu Andong", "Bambu Tamiang", "Bambu Ater", "Bambu Betung"),
            1
        )
    )

}