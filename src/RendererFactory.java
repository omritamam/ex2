public class RendererFactory {
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
