# electronic-billboard
1. BillboardServer
The Server is a command-line program that allows connection from the control panel
and view using socket channel and byte buffer. Its main jobs are interfacing with
database, handling requests sent from clients.

2. BilllboardControlPanel
It is a GUI application using to create new billboards, modify existing billboards,
schedule billboards and maintaining the users’ details. The application will display Login
Page in the first start up. Users need to enter their username and password to continue
and it is the only way to access to the system. The default user has a username and a
password is “admin” and “admin” respectively. Administrators will have full of
permissions: “Create Billboards”, “Edit Billboards”, “Schedule Billboards” and “Edit
Users”. 

3. BillboardViewer
It is a GUI application using to display scheduled billboards. The Billboard Viewer
connects to a server to retrieve the information about presently scheduled billboard
every 15 seconds. If there is no current billboard to display or error occurs when
connecting to the server, an error screen will be shown. Users can exit the application
by pressing the ‘Escape’ key on a keyboard or clicking the mouse anywhere on the
billboard screen. The presentation of a billboard depends on the number of attributes of
that billboard.
