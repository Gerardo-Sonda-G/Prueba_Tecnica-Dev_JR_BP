# Prueba_Tecnica-Dev_JR_BP

  Definicion de la estructura de la base de datos, modelos o entidades y estructura base de los query y mutations implementando graphQl

Estructrua y relación de las tablas en la base de datos:

![esquema grafico de las tables](/images/schema.png)

La llave foranea de las tabla de los tickets tiene una
restricción en cascada. para evitar problemas al eliminar o acutalizar un evento

Posteriormente se definieron los modelos en java

Modelo de la tabla de eventos. 

    private long id;
    private String name;
    private String type;
    private int amount;
    private String start_date;
    private String end_date;
    private int tickets_number;
    private int sold_tickets;
    private int changed_tickets;
    private String address;

Modelo de la tabla de tickets.

    private long id;
    private String ticket_date;
    private String email;
    private long event_id;
    private boolean changed;

En el proceso de planeación de la api se analizaron las posibles diferentes rutas y methos para definir los endpoints
sin embargo durante el proceso de planecación, decidi tomar el reto de implementar por primera vez
graphQl para optimizar la informacion que se obtiene de las peticiones. 

A continuacion se muestra las estructuras definidas durante este proceso.


Estructura de las mutaciones de los eventos
  
    createEvent(input:{
    name: String
    type: String
    amount:Int
    start_date: String
    end_date: String
    event_date:String
    tickets_number: Int
    address:String
    }): Event

    deleteEvent(id:ID): Event

    updateEvent(input:{
    id: ID!
    name: String
    type: String
    amount:Int
    start_date: String
    end_date: String
    tickets_number: Int
    changed_tickets: Int
    sold_tickets: Int
    address:String
    }): Event
Estructura de las mutaciones de los boletos

    createTicket(input : {    
    ticket_date: String
    email: String
    event_id: Int
    }) : Ticket

    changeTicket(id:ID!,email:String ) : Ticket

Querys de los eventos

    getEvents: [Event]
    getEvent(id:ID):Event


