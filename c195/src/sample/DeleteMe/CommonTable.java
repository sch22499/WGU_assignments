package sample.DeleteMe;

import javafx.scene.control.TableView;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CommonTable<TEntity>
{
    private List<LDataReporter> tableDataReporters;

    public CommonTable(Class<TEntity> reportedType)
    {
        tableDataReporters = new ArrayList<>();

        Field[] fields = reportedType.getFields();
        for (Field field : fields)
        {
            Annotation[] fieldAnnotations = field.getAnnotations();
            boolean fieldIsViewableByUser = false;
            for (Annotation annotation : fieldAnnotations)
            {
                if (annotation instanceof ViewableData)
                {
                    fieldIsViewableByUser = true;
                    break;
                }
            }

            if (fieldIsViewableByUser)
            {
                LDataReporter fieldDataReporter = (entity) ->
                {
                    try
                    {
                        return field.get(entity).toString();
                    }
                    catch (Exception e)
                    {
                        // Handle exception.
                    }
                    return "";
                };
                tableDataReporters.add(fieldDataReporter);
            }
        }
    }

    public TableView generateTableView(List<TEntity> tableData)
    {
        List<List<String>> tableRows = new ArrayList<>();
        for (TEntity rowModel : tableData)
        {
            List<String> rowValues = new ArrayList<>();
            for (LDataReporter dataReporter : tableDataReporters)
            {
                String dataValue = dataReporter.getData(rowModel);
                rowValues.add(dataValue);
            }
            tableRows.add(rowValues);
        }

        // Instantiate, populate, and return a TableView using tableRows.

        return null;
    }
}
