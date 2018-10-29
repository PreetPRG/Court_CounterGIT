using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(EventManager4.Startup))]
namespace EventManager4
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
