package id.co.culturefluent.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.co.culturefluent.data.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging) // <-- this is the important line!

        return Retrofit.Builder()
            .baseUrl("https://asia-southeast2-caramel-pulsar-315700.cloudfunctions.net/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

    @Provides
    fun provideClassifyService(retrofit: Retrofit): ClassifyService = retrofit.create(
        ClassifyService::class.java)

    @Singleton
    @Provides
    fun provideClassifyDataSource(classifyService: ClassifyService) = ClassifyDataSource(classifyService)

    @Singleton
    @Provides
    fun provideClassifyRepository(classifyDataSource: ClassifyDataSource) =
        ClassifyRepository(classifyDataSource)

    @Provides
    fun provideReportService(retrofit: Retrofit): ReportService = retrofit.create(
        ReportService::class.java)

    @Singleton
    @Provides
    fun provideReportDataSource(reportService: ReportService) = ReportDataSource(reportService)

    @Singleton
    @Provides
    fun provideReportRepository(reportDataSource: ReportDataSource) =
        ReportRepository(reportDataSource)

}