public class RendererFactory {
    /***
     *
     * @param RendererType - String = console/none
     * @return Renderer object for valid input, null otherwise
     */
    public Renderer buildRenderer(String RendererType){
        switch (RendererType){
            case "console":
                return new ConsoleRenderer();
            case "none":
                return new VoidRenderer();
        }
        return null;
    }
}
