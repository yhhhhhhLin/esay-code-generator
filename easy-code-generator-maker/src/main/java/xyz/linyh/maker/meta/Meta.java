package xyz.linyh.maker.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Meta {

    private String name;
    private String description;
    private String basePackage;
    private String version;
    private String author;
    private String createTime;
    private FileConfig fileConfig;
    private ModelsConfig modelConfig;

    @NoArgsConstructor
    @Data
    public static class FileConfig {
        private String inputRootPath;
        private String outputRootPath;
        private String type;
        private List<Files> files;

        @NoArgsConstructor
        @Data
        public static class Files {
            private String inputPath;
            private String outputPath;
            private String type;
            private String generateType;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ModelsConfig {
        private List<Models> models;

        @NoArgsConstructor
        @Data
        public static class Models {
            private String fieldName;
            private String type;
            private String description;
            private Object defaultValue;
            private String abbr;
        }
    }
}
