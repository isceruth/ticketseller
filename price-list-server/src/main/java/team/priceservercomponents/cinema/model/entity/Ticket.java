package team.priceservercomponents.cinema.model.entity;

import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public final class Ticket {

    Ticket() {}

    private Ticket(Builder builder) {
        Preconditions.checkNotNull(builder.seat, "seat");
        Preconditions.checkNotNull(builder.price, "price");
        Preconditions.checkNotNull(builder.session, "session");
        this.seat = builder.seat;
        this.price = builder.price;
        this.owner = builder.owner;
        this.session = builder.session;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "place")
    private Long seat;

    @Column(name = "price")
    private Long price;

    @Column(name = "premium")
    private Boolean premium;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    public Long getId() {
        return id;
    }

    public Long getSeat() {
        return seat;
    }

    public Long getPrice() {
        return price;
    }

    public Boolean getPremium() {
        return premium;
    }

    public User getOwner() {
        return owner;
    }

    public Session getSession() {
        return session;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public static class Builder {
        private Long seat;
        private Long price;
        private User owner;
        private Session session;
        private Boolean premium;

        public Builder setSeat(long seat) {
            this.seat = seat;
            return this;
        }

        public Builder setPrice(long price) {
            this.price = price;
            return this;
        }

        public Builder setOwner(User user) {
            this.owner = user;
            return this;
        }

        public Builder setPremium(Boolean premium) {
            this.premium = premium;
            return this;
        }

        public Builder setSession(Session session) {
            this.session = session;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}
