function clearOption(selectId) {
    var x = document.getElementById(selectId)
    while (x.length > 0)
    {
        x.remove(x.length - 1);
    }
}