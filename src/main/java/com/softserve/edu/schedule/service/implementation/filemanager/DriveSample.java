
package com.softserve.edu.schedule.service.implementation.filemanager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

import org.springframework.stereotype.Component;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

@Component
public class DriveSample {

	private String APPLICATION_NAME = "Drive API Java Quickstart";

	private String UPLOAD_FILE_PATH = "E:\\some.bmp";
	/* private String DIR_FOR_DOWNLOADS = "E:\\"; */
	private java.io.File UPLOAD_FILE = new java.io.File(UPLOAD_FILE_PATH);

	private java.io.File DATA_STORE_DIR = new java.io.File(
	        System.getProperty("user.home"),
	        ".credentials/drive-java-quickstart");

	private FileDataStoreFactory dataStoreFactory;

	private HttpTransport httpTransport;

	private JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private Drive drive;

	public DriveSample() {
		try {
			authorize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Credential authorize() throws Exception {

		GoogleClientSecrets clientSecrets = GoogleClientSecrets
		        .load(JSON_FACTORY, new InputStreamReader(DriveSample.class
		                .getResourceAsStream("/client_secrets.json")));

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		        httpTransport, JSON_FACTORY, clientSecrets,
		        Collections.singleton(DriveScopes.DRIVE_FILE))
		                .setDataStoreFactory(dataStoreFactory).build();

		return new AuthorizationCodeInstalledApp(flow,
		        new LocalServerReceiver()).authorize("user");
	}

	public void beginUpload() {
		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

			Credential credential = authorize();

			drive = new Drive.Builder(httpTransport, JSON_FACTORY, credential)
			        .setApplicationName(APPLICATION_NAME).build();

			View.header1("Starting Resumable Media Upload");
			uploadFile(true);

			View.header1("Success!");
			return;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private File uploadFile(boolean useDirectUpload) throws IOException {
		File fileMetadata = new File();
		fileMetadata.setTitle(UPLOAD_FILE.getName());
		System.err.println(UPLOAD_FILE.getName());

		FileContent mediaContent = new FileContent("image/jpeg", UPLOAD_FILE);

		Drive.Files.Insert insert = drive.files().insert(fileMetadata,
		        mediaContent);
		MediaHttpUploader uploader = insert.getMediaHttpUploader();
		uploader.setDirectUploadEnabled(useDirectUpload);
		uploader.setProgressListener(new FileUploadProgressListener());
		return insert.execute();
	}
}
