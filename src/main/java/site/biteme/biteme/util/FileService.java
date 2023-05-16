package site.biteme.biteme.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.biteme.biteme.global.exception.ErrorCode;
import site.biteme.biteme.global.exception.file.FileStoreException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileService {
    @Value("${file.upload.path}")// yml 설정파일
    private String fileUploadPath;

    // return fullFilePath
    public List<String> saveAll(List<MultipartFile> multipartFiles){
        List<String> fullFilePaths = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            // empty Check. type=file 이며 name이 일치한다면, 본문이 비어있어도 MultiPartFile 객체가 생성된다.
            if (multipartFile.isEmpty()){
                continue;
            }
            String originalFileName = multipartFile.getOriginalFilename();
            String fullFilePath = createFullFilePath(originalFileName);

            try {
                multipartFile.transferTo(new File(fullFilePath));
            } catch (IOException e) {
                // FileStoreException 발생시키기 전에, IOEXCEPTION 에 대한 로그를 남긴다.
                log.error("IOEXCEPTION: " + originalFileName + " 저장 불가" );
                e.printStackTrace();
                throw new FileStoreException(ErrorCode.FILE_CANNOT_BE_STORED);
            }
            fullFilePaths.add(fullFilePath);
        }
        // 저장한 파일의 경로 리스트를 반환한다.
        return fullFilePaths;
    }

    private String createFullFilePath(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFileName);

        String storedFileName = uuid + "." + ext;
        return fileUploadPath + storedFileName;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos +1);
    }
}
