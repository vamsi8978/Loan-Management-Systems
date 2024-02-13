import { Component } from '@angular/core';
import { HttpClient, HttpEventType, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent {
  selectedFiles: File[] = [];
  overallProgress = 0;

  constructor(private http: HttpClient) {}

  onFileSelected(event: any, documentType: string): void {
    const file: File = event.target.files[0];

    if (this.isValidFileType(file)) {
      const progressPerFile = 20;
      this.overallProgress = Math.min((this.selectedFiles.length + 1) * progressPerFile, 100);
      this.selectedFiles.push(file);
    } else {
      alert('Invalid file type. Please select only PDF or Word files.');
    }
  }

  onUpload(): void {
    const formData = new FormData();
    this.selectedFiles.forEach(file => {
      if (file) {
        formData.append('file', file);
      }
    });

    this.http.post('http://localhost:8080/upload', formData, {
      reportProgress: true,
      observe: 'events'
    })
    .subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        // Do nothing for individual file progress, handled in onFileSelected
      } else if (event instanceof HttpResponse) {
        console.log('Files uploaded successfully', event);
      }
    }, error => {
      console.error('Error uploading files', error);
    });
  }

  isValidFileType(file: File): boolean {
    const supportedTypes = ['application/pdf', 'application/msword', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'];
    const extension = file.name.split('.').pop()?.toLowerCase();
    return supportedTypes.includes(file.type) && (extension === 'pdf' || extension === 'doc' || extension === 'docx');
  }
}