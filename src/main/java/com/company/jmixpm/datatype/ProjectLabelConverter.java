package com.company.jmixpm.datatype;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProjectLabelConverter implements AttributeConverter<ProjectLabels, String> {

    @Override
    public String convertToDatabaseColumn(ProjectLabels attribute) {
        return attribute != null
                ? Joiner.on(", ").join(attribute.getLabels())
                : "";
    }

    @Override
    public ProjectLabels convertToEntityAttribute(String dbData) {
        return dbData != null
                ? new ProjectLabels(Splitter.on(",").splitToList(dbData))
                : null;
    }
}
