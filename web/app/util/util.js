/**
 * Creación de la instancia de una libreria para dibujar tablas facilmente
 * 
 * Ejemplo para usarla
 * 
 * 
 * @param {type} window
 * @returns {undefined}
 */
(function (window)
{
    /**
     * Constructor del objeto.
     * @param {type} options objeto con las opciones de la configuración de
     * la librería
     *      columns: columnas de la tabla. Es un objeto que contiene dos atributos:
     *          {
     *              label: Este atributo será el nombre de la columna
     *              getValue:function(objeto){... return textoDeLaCelda } 
     *                  esta función retorna el valor de la celda, el atributo objeto
     *                  es recibido como parámetro, internamente es la fila actual que se dibuja.
     *          }
     *      id:id de la tabla
     *      className: clases estilos de la tabla
     *      theadMarkup: tipo de etiqueta usado para las celdas en el thead "th" o "td"
     tbodyMarkup: tipo de etiqueta usado para las celdas en el tbody "th" o "td"
     title: titulo de la tabla
     onPreDraw: callback que se ejecutará antes de dibujar la tabla,
     onPostDraw: callback que se ejecutará después de dibujar la tabla
     
     Un ejemplo de uso se puede ver en el script 
     webroot\js\views\reports\sold_products.js
     
     * @returns {global-custom-functionsL#357._superTable}
     */
    var _superTable = function (options) {
        _superTable.columns = options.columns;
        _superTable.options = {
            id: (options.id ? options.id : null),
            className: (options.className ? options.className : null),
            theadMarkup: (options.theadMarkup ? options.theadMarkup : TH_CELL_MARKUP),
            tbodyMarkup: (options.tbodyMarkup ? options.tbodyMarkup : TD_CELL_MARKUP),
            title: (options.title ? options.title : null),
            onPreDraw: options.onPreDraw,
            onPostDraw: options.onPostDraw
        }
        return _superTable;
    }
    /**
     * Función para eliminar la tabla 
     * @param {type} options, objeto que contiene las opciones para configurar
     * como eliminará la tabla
     *      onPreDie: callback que se ejecutará antes de eliminar la tabla
     *      onPosDie: callback que se ejecutará después de eliminar la tabla
     * @returns {undefined}
     */
    _superTable.die = function (options)
    {
        tryCallFunction(options.onPreDie)
        $(_superTable.containerSelector).html("");
        tryCallFunction(options.onPosDie)
    }

    /**
     * Función que después de construido el objeto de la librería dibuja la tabla
     * 
     * @param {type} options {
     *      options.id: id de la tabla
     *      options.className: clases de la tabla
     * }    
     * @param {type} containerSelector
     * @param {type} rows
     * @returns {Boolean}
     */
    _superTable.draw = function (options, containerSelector, rows) {

        var valide = valideDraw(containerSelector, rows);
        if (valide === true) {
            tryCallFunction(_superTable.options.onPreDraw);
            _superTable.containerSelector = containerSelector;
            _superTable.rows = rows;
            _superTable.id = options.id ? options.id : null;
            _superTable.className = options.id ? options.className : null;

            var id = (_superTable.id != null ? " id=\"" + _superTable.id + "\" " : "");
            var className = (_superTable.id != null ? " class=\"" + _superTable.className + "\" " : "");
            var html = _superTable.options.title ? ("<h3>" + _superTable.options.title + "</h3>") : "";
            html += "<table" + id + className + ">"
            html += "<thead>" + drawHeader(_superTable.TH_CELL_MARKUP, _superTable.columns) + "</thead>"
            html += "<tbody>" + drawRows(_superTable.TD_CELL_MARKUP, _superTable.columns, _superTable.rows) + "</tbody>"
            html += "</table>";

            $(_superTable.containerSelector).html(html);

            tryCallFunction(_superTable.options.onPostDraw);
            return true;
        } else
        {
            console.error(valide);
            return false;
        }

    }
    /**
     * Función que valida si puede dibujar la tabla
     * @param {type} containerSelector selector de la tabla
     * @param {type} rows filas que se añadirán
     * @returns {String|Boolean}
     */
    function valideDraw(containerSelector, rows)
    {

        if (!_superTable.columns) {
            return("Please add the columns object");
        }
        if (!rows && !_superTable.rows)
        {
            return("Rows were not specified");
        }
        if (typeof containerSelector != "string")
        {
            return "Container Selector were not specified";
        }



        return true;
    }



    //private functions


    function tryCallFunction(posibleFunction) {

        if (posibleFunction && typeof posibleFunction == "function")
        {
            return posibleFunction();
        }
    }


    /**
     * Función que crea la fila en html para ser añadida al theader
     * @returns {undefined}
     */
    function drawHeader(markup, columns) {
        var html = "<tr>"
        for (var column in columns)
        {
            html += "<" + markup + ">" +
                    columns[column].label +
                    "</" + markup + ">";
        }
        html += "</tr>";
        return html;
    }

    function drawRows(markup, columns, rows)
    {
        var html = "";

        for (let row in rows)
        {
            html += "<tr>";
            html += drawCols(markup, columns, rows[row]);
            html += "</tr>";
        }
        return html;
    }

    function drawCols(markup, columns, rowData)
    {
        var html = "";
        var markup = markup;
        for (let column in columns)
        {
            html += "<" + markup + ">" +
                    columns[column].getValue(column, columns, rowData) +
                    "</" + markup + ">";
        }
        return html;
    }

    _superTable.TH_CELL_MARKUP = "th";
    _superTable.TD_CELL_MARKUP = "td";




    window.superTables = _superTable;
})(window);
var superTables = window.superTables;

